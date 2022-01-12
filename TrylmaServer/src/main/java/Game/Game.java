package Game;

import Board.*;
import socketServer.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 
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

	public Game(List<Player> players) {
        this.players = players;
        this.winners = new ArrayList<>();
        this.playersCount = players.size();
        this.rules = new TrylmaRules(playersCount);
        this.currentPlayer = PlayerColor.BLUE.playerColorID;
        this.converter = new JSONBoardConverter();

        DefaultBoardBuilder boardBuilder = new DefaultBoardBuilder();
        boardBuilder.initializeBoardFields();
        boardBuilder.assignFields(playersCount);
        this.board = boardBuilder.getDefaultBoard();

        for (Player player : players) {
            player.setGame(this);
            player.sendMessage(getInitializationData(player.getPlayerId()));
        }
	}

    public String getInitializationData (int playerID) {
        String jsonBoard = converter.buildJSONBoard(board);
        //System.out.println(playerID + "|" + jsonBoard + "|" + currentPlayer);
        return playerID + "|" + jsonBoard + "|" + currentPlayer;
    }

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
             converter = new JSONBoardConverter();
             String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board) + "|" + currentPlayer;
             sendToPlayers(updatedBoard);
         }
         else{
             //wyślij wiadomość niepoprawny ruch
             //wyczyść wyś
             this.resetFieldsStatus();
             converter = new JSONBoardConverter();
             String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board) + "|" + currentPlayer;
             sendToPlayers(updatedBoard);

         }
         
        if (this.getRules().isWinner(this, playerId)){
            this.sendToPlayer(playerId, "YOU_WON");
            this.winners.add(playerId);
            System.out.println("Player " + playerId + "won the game");
        }

        this.getRules().resetAvailableFields();


     }

    public boolean playerStillPlaying(int playerId){
        boolean isWinner = false;
        for(int i = 0; i < winners.size(); i++)
            if(winners.get(i) == playerId)
                isWinner = true;
        if(isWinner)
            return false;
        else return true;
    }
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

    public void resetFieldsStatus() {
        for (BoardField field : this.getBoard().fields) {
            field.setStatusColor(StatusColor.INACTIVE);
        }
    }

    public void sendToPlayers(String message){
        for(Player player : players) {
            player.sendMessage(message);
        }
    }

    public void sendToPlayer(int id, String message){
        for(Player player : players) {
            if (player.getPlayerId() == id) {
                player.sendMessage(message);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public TrylmaRules getRules() {
        return rules;
    }
}
