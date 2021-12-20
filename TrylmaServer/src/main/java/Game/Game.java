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
    DefaultBoardBuilder boardBuilder;
    Board board;

	public Game(List<Player> players) {
	this.players = players;
    this.playersCount = players.size();

    DefaultBoardBuilder boardBuilder = new DefaultBoardBuilder();
    boardBuilder.initializeBoardFields();
    this.board = boardBuilder.getDefaultBoard();
        for (Player player:players) {
            player.setGame(this);
            System.out.println("zaraz wysle wiadomosc do klienta");
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
 /*public synchronized void showMessage(int player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        } else if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (board[location] != null) {
            throw new IllegalStateException("Cell already occupied");
        }
    }*/

    public Board getBoard(){
        return board;
    }
}
