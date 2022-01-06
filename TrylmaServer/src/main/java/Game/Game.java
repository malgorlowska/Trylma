package Game;

import Board.*;
import socketServer.Player;
import java.util.List;

/**
 * 
 *
 */
public class Game {
    int playersCount;
	int currentPlayer;
    public static List<Player> players;
    TrylmaRules rules;
    Board board;
    JSONBoardConverter converter;

	public Game(List<Player> players) {
        Game.players = players;
        this.playersCount = players.size();
        this.rules = new TrylmaRules();
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
        return playerID + "|" + jsonBoard;
    }

     public synchronized void move(int startMoveField, int endMoveField, int playerId) {
         if (playerId != currentPlayer) {
             throw new IllegalStateException("Not your turn");
         }
         converter = new JSONBoardConverter();

         if (rules.moveIsCorrect(this.getBoard(), endMoveField)) {
             board.fields.get(startMoveField).setCurrentPlayerColor(PlayerColor.NO_PLAYER);
             board.fields.get(endMoveField).setCurrentPlayerColor(PlayerColor.fromInteger(playerId));
             this.resetFieldsStatus();

             converter = new JSONBoardConverter();
             String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board);
             sendToPlayers(updatedBoard);

             // managing players queue
             currentPlayer = currentPlayer % playersCount + 1;
         }

         // TODO check if someone won the game

     }

    public synchronized void showPossibilities(int startMoveField, int playerId) {
        if (playerId != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        }
        this.getRules().setAvailableFields(this.getBoard(), startMoveField, true);

        for (int fieldID : this.getRules().getAvailableFields()) {
            this.getBoard().fields.get(fieldID).setCurrentStatusColor(StatusColor.RED);
        }

        converter = new JSONBoardConverter();
        String updatedBoard = "UPDATE_BOARD|" + converter.buildJSONBoard(board);
        sendToPlayer(playerId, updatedBoard);
    }

    public void resetFieldsStatus() {
        for (BoardField field : this.getBoard().fields) {
            field.setCurrentStatusColor(StatusColor.GREEN);
        }
    }

    public Board getBoard() {
        return board;
    }

    public TrylmaRules getRules() {
        return rules;
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

}
