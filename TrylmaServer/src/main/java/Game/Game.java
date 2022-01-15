package Game;

import Board.*;
import socketServer.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * The game class manages the game flow.
 * It determines player queue, sends messages to right players,
 * checks if move is correct, updates the game board
 *
 */
public class Game {
    int playersCount;
	int currentPlayer;
    public List<Integer> winners;
    public static List<Player> players;
    TrylmaRules rules;
    Board board;
    JSONBoardConverter converter;

    /**
     * Constructor
     * Sets all necessary variables,
     * sends board and other info to players
     * at the start of the game
     * @param players list of players who play this game
     * @param boardSize selected size of a game board
     *
     */
	public Game(List<Player> players, int boardSize) {
        Game.players = players;
        this.winners = new ArrayList<>();
        this.playersCount = players.size();
        this.rules = new TrylmaRules(playersCount);
        this.currentPlayer = PlayerColor.BLUE.playerColorID;
        this.converter = new JSONBoardConverter();

        DefaultBoardBuilder boardBuilder = new DefaultBoardBuilder();
        BoardShapes boardShapes = new BoardShapes(boardSize);
        boardBuilder.initializeBoardFields(boardShapes.getSelectedBoard());
        boardBuilder.assignFields(playersCount);
        this.board = boardBuilder.getDefaultBoard();

        for (Player player : players) {
            player.setGame(this);
            player.sendMessage(getInitializationData(player.getPlayerId()));
        }
	}

    /**
     * Creates all necessary data that needs to be delivered
     * to player when starting the game
     *
     * @param playerID id of player we are sending the data
     * @return all the data in form of a String
     *
     */
    public String getInitializationData (int playerID) {
        String jsonBoard = converter.buildJSONBoard(board);
        return playerID + "|" + jsonBoard + "|" + currentPlayer;
    }

    /**
     * Method being used after player first click,
     * based on the field he/she selected
     * it maps available fields in game rules
     * and sends them to player.
     * @param startMoveField the field selected on first click
     * @param playerId id of a player who is trying to move
     *
     */
    public synchronized void showPossibilities(int startMoveField, int playerId) {
        if (playerId != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        }
        resetFieldsStatus();
        this.getRules().resetAvailableFields();
        BoardField startField = this.getBoard().fields.get(startMoveField);
        this.getRules().setAvailableFields(this.getBoard(), startField, true);


        startField.setStatusColor(StatusColor.ACTIVE);
        for (int fieldID : this.getRules().getAvailableFields()) {
            this.getBoard().fields.get(fieldID).setStatusColor(StatusColor.POSSIBLE_MOVE);
        }

        converter = new JSONBoardConverter();
        String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board) + "|" + playerId;
        sendToPlayer(playerId, updatedBoard);
    }

    /**
     * Method being used after player second click,
     * based on the field he/she selected,
     * it checks if the move is legal and if someone won.
     * After move sends updated board to all players
     *
     * @param startMoveField the field selected on first click
     * @param endMoveField the field selected on second click
     * @param playerId id of a player who is trying to move
     */
     public synchronized void move(int startMoveField, int endMoveField, int playerId) {
         if (playerId != currentPlayer) {
             throw new IllegalStateException("Not your turn");
         }
         converter = new JSONBoardConverter();

         if (rules.moveIsCorrect(this.getBoard(), endMoveField)) {
             board.fields.get(startMoveField).setPlayerColor(PlayerColor.NO_PLAYER);
             board.fields.get(endMoveField).setPlayerColor(PlayerColor.fromInteger(playerId));
             this.resetFieldsStatus();

             // managing players queue
             currentPlayer = currentPlayer % playersCount + 1;

             while(!playerStillPlaying(currentPlayer))
                currentPlayer = currentPlayer % playersCount + 1;
         }
         else {
             this.resetFieldsStatus();
         }

         converter = new JSONBoardConverter();
         String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board) + "|" + currentPlayer;
         sendToPlayers(updatedBoard);
         
        if (this.getRules().isWinner(this, playerId)){
            this.sendToPlayer(playerId, "YOU_WON");
            this.winners.add(playerId);
            System.out.println("Player " + playerId + "won the game");
        }

        this.getRules().resetAvailableFields();
     }

    /**
     * Checks if given player is still playing the game
     * @param playerId player we are checking
     * @return true if player is still in the game
     *
     */
    public boolean playerStillPlaying(int playerId){
        boolean isWinner = false;
        for (Integer winner : winners)
            if (winner == playerId) {
                isWinner = true;
                break;
            }
        if (isWinner)
            return false;
        else return true;
    }

    /**
     * Resets all board fields status
     * (Sets it to inactive)
     *
     */
    public void resetFieldsStatus() {
        for (BoardField field : this.getBoard().fields) {
            field.setStatusColor(StatusColor.INACTIVE);
        }
    }

    /**
     * Sends the message to all players
     * @param message message we want to send
     *
     */
    public void sendToPlayers(String message){
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    /**
     * Sends message only to given player
     * @param id player id
     * @param message message we want to send
     */
    public void sendToPlayer(int id, String message){
        for (Player player : players) {
            if (player.getPlayerId() == id) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * @return returns this game board
     *
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets id of current player to id of given player
     * @param id given player id
     *
     */
    public void setCurrentPlayer(int id){
        this.currentPlayer = id;
    }

    /**
     * @return returns ruleset of this game
     *
     */
    public TrylmaRules getRules() {
        return rules;
    }
}
