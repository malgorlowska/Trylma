package socketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 */
public class Game //singleton?
{
/*
	int playersCount;
	int currentPlayer;
	public Game(int playersCount) {
	this.playersCount = playersCount;	
	}
	
 public synchronized void showMessage(int player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        } /*else if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (board[location] != null) {
            throw new IllegalStateException("Cell already occupied");
        }*/
        
      //  currentPlayer = (currentPlayer + 1)%playersCount;
    //}
}
