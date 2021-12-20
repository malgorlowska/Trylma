package Game;

import Board.Board;
import Board.PlayerColor;
import Board.DefaultBoardBuilder;
import socketServer.Player;
import Board.JSONBoardConverter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 */
public class Game //singleton
{

    int playersCount;
	int currentPlayer;
    public static List<Player> players;
    TrylmaRules rules;
    Board board;
    JSONBoardConverter converter;

	public Game(List<Player> players) {
	this.players = players;
    this.playersCount = players.size();
    this.rules = new TrylmaRules();
    currentPlayer = 1;
    converter = new JSONBoardConverter();
    DefaultBoardBuilder boardBuilder = new DefaultBoardBuilder();
    boardBuilder.initializeBoardFields();
    boardBuilder.assignFields(playersCount);
    this.board = boardBuilder.getDefaultBoard();
        for (Player player : players) {
            player.setGame(this);
            player.sendMessage(getInitializationData(player.getId()));
        }
	}

    public String getInitializationData (int playerID) {
        String data;
        String jsonBoard = converter.buildJSONBoard(board);
        data = playerID + "|" + jsonBoard;
        return data;
    }

     public synchronized void move(int startMoveField, int endMoveField, int id) {
         if (id != currentPlayer) {
             throw new IllegalStateException("Not your turn");}
         String boardBefore = converter.buildJSONBoard(board);
         board.fields.get(startMoveField).setCurrentPlayerColor(PlayerColor.NO_PLAYER);
         board.fields.get(endMoveField).setCurrentPlayerColor(PlayerColor.fromInteger(id));
         String updatedBoard;
         updatedBoard = "UPDATEBOARD|" + converter.buildJSONBoard(board);
         //updatedBoard = converter.buildJSONBoard(board);
         System.out.println("Porownanie " + boardBefore.equals(updatedBoard));
         sendToPlayers(updatedBoard);
         //sprawdzenie czy jego ruch jest poprawny - w odpowiedniej odległości, nie jest zajęte itp
         //uaktualnienie planszy w serwerze
         //sprawdzenie czy nie wygrał
         //przesłanie do wszystkich uaktualnionej planszy
         if(currentPlayer == 1) //uwaga dziala tylko dla dwóch graczy -> do poprawy
             currentPlayer = 2;
         else
             currentPlayer =1;
     }

    public Board getBoard() {
        return board;
    }

    public TrylmaRules getRules() {
        return rules;
    }

    public void sendToPlayers(String message){
        for(Player player:players)
            player.sendMessage(message);
    }


}
