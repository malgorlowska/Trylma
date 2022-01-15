package socketClient;

import board.*;
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
 * Socket class that is responsible
 * for communication with the server
 *
 */
public class SocketClient {
    private PlayerColor playerColor;
    public PrintWriter out = null;
    public Scanner scanner = null;
    public DataInputStream input = null;
    ApplicationWindow appWindow = null;
    Socket socket = null;
    int port;
    
    /**
     * Basic constructor.
     *
     * */
    public SocketClient(int port) {
    	System.out.println("Client");
        this.port = port;
        this.setUp();
	}
    
    /**
     * Connects to the socket, receives data,
     * that is needed to start the game, from the server.
     *
     */
    public void listenSocket() {
        String message = scanner.nextLine();
        String JSONBoard = message.split("[|]")[1];
        int playerID = Integer.parseInt(message.split("[|]")[0]);
        int currentPlayerID = Integer.parseInt(message.split("[|]")[2]);

        setPlayerColor(playerID);

        this.appWindow = new ApplicationWindow(playerColor, currentPlayerID, JSONBoard);
        this.appWindow.getBoard().setPlayer(this);
        this.appWindow.setVisible(true);
    }

    /**
     * Takes suitable actions according to
     * data received from the server
     *
     */
    public void play() {
        String message;

        while(scanner.hasNextLine()) {
            message = scanner.nextLine();
            String command = message.split("[|]")[0];

            if (command.equals("UPDATE_BOARD")) {
                String updatedBoard = message.split("[|]")[1];
                int currentPlayer = Integer.parseInt(message.split("[|]")[2]);

                this.appWindow.setBoard(updatedBoard);
                this.appWindow.setCurrPlayer(currentPlayer);

                this.appWindow.repaint();
                this.appWindow.getBoard().repaint();
            }
            else if (command.equals("YOU_WON")){
                System.out.println("you won the game");
                JOptionPane.showMessageDialog(this.appWindow, "Congratulations!\n You won the game.",
                        "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Sets up all necessary tools
     *
     */
    public void setUp() {
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
            input = new DataInputStream(socket.getInputStream());
        }catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * @return returns our color
     *
     */
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Sets our color based on
     * data received from the server
     *
     * @param playerColor id of our color
     *
     */
    public void setPlayerColor(int playerColor) {
        this.playerColor = PlayerColor.fromInteger(playerColor);
    }
}
