package Game;

import Board.Board;
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

	public Game(List<Player> players) {
	this.players = players;
    this.playersCount = players.size();
    this.rules = new TrylmaRules();
    currentPlayer = 1;
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
        JSONBoardConverter converter = new JSONBoardConverter();
        String jsonBoard = converter.buildJSONBoard(board);
        data = playerID + "|" + jsonBoard;
        return data;
    }

     public synchronized void move(String command, int id) {
         if (id != currentPlayer) {
             throw new IllegalStateException("Not your turn");}
         //uaktualnienie planszy w serwerze
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

    public void sendToPlayers(String message){
        for(Player player:players)
            player.sendMessage(message);
    }


}
