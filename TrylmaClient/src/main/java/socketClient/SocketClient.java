package socketClient;

import board.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import frontend.ApplicationWindow;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class SocketClient {
	private int playerId;
    private PlayerColor playerColor;
    public PrintWriter out = null;
    //public BufferedReader in = null;
    public Scanner scanner = null;
    public DataInputStream input = null;
    ApplicationWindow appWindow = null;
    Socket socket = null;

    int port;
    
    /** Basic constructor. */
    public SocketClient(int port) {
    	System.out.println("Client");
        this.port = port;
	}
    
    /**
     * Connects to the socket, exchanges data with the server.
     */
    public void listenSocket() {
        try {
          socket = new Socket("localhost", port);
          out = new PrintWriter(socket.getOutputStream(), true);
          scanner = new Scanner(socket.getInputStream());
         // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          input = new DataInputStream(socket.getInputStream());

          String message = scanner.nextLine();
          String JSONBoard = message.split("[|]")[1];
          int playerID = Integer.parseInt(message.split("[|]")[0]);
          int currentPlayerID = Integer.parseInt(message.split("[|]")[2]);

          setPlayerId(playerID);
          setPlayerColor(PlayerColor.fromInteger(getPlayerId()));

          this.appWindow = new ApplicationWindow(playerId, currentPlayerID, JSONBoard);
          this.appWindow.getBoard().setPlayer(this);

          System.out.println("My id is " + getPlayerId());
          System.out.println("My color is " + getPlayerColor().toString());

          this.appWindow.setVisible(true);
        }
        catch (UnknownHostException e) {
           System.out.println("Unknown host: localhost"); System.exit(1);
        }
         catch  (IOException e) {
           System.out.println("No I/O"); System.exit(1);
           System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void play() throws IOException {
        String message;

        while(scanner.hasNextLine()) {
            message = scanner.nextLine();
            System.out.println("I received a message : " + message);
            String command = message.split("[|]")[0];

            if (command.equals("UPDATE_BOARD")) {
                System.out.println("updating the board ...");
                String updatedBoard = message.split("[|]")[1];
                int currentPlayer = Integer.parseInt(message.split("[|]")[2]);

                this.appWindow.setBoard(updatedBoard);
                this.appWindow.setCurrPlayer(currentPlayer);

                this.appWindow.repaint();
                this.appWindow.getBoard().repaint();
            }

            if (command.equals("YOU_WON")){
                System.out.println("you won the game");
                JOptionPane.showMessageDialog(this.appWindow, "Congratulations!\n You won the game.",
                        "INFORMATION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }


}
