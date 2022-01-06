package socketClient;

import board.*;
import frontend.ApplicationWindow;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class SocketClient 
{
	private int playerId;
    private PlayerColor playerColor;
    private Board board;
	Socket socket = null;
    public PrintWriter out = null;
    public BufferedReader in = null;
    public Scanner scanner = null;
    public DataInputStream input = null;
    public ObjectInputStream objectInput = null;
    ApplicationWindow application = null;
    DefaultBoardBuilder builder = null;
    
    int port;
    
    /** Basic constructor. */
    public SocketClient(int port) {

    	System.out.println("Client");
        this.port = port;

	}
    
    /**
     * Connects to the socket, exchanges data with the server.
     */
    public void listenSocket()
    {
        try {
        	System.out.println("open socket");
          socket = new Socket("localhost", port);

          // Connecting with socket
          // Sending to the server
          out = new PrintWriter(socket.getOutputStream(), true);
          // Receiving strings from server
          //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(socket.getInputStream());
            String[] receivedData = scanner.nextLine().split("[|]");
          //String[] receivedData = in.readLine().split("[|]");
          setPlayerId(Integer.parseInt(receivedData[0]));
          setPlayerColor(PlayerColor.fromInteger(getPlayerId()));
          builder = new DefaultBoardBuilder();
          setBoard(receivedData[1]);

          this.board.setPlayer(this);
          System.out.println("My id is " + getPlayerId());
          System.out.println("My color is " + getPlayerColor().toString());
            application = new ApplicationWindow(this);
            application.setVisible(true);
        }
        catch (UnknownHostException e) 
        {
           System.out.println("Unknown host: localhost"); System.exit(1);
        }
         catch  (IOException e) {
           System.out.println("No I/O"); System.exit(1);
           System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void play() throws IOException {
        String message;
        while(scanner.hasNextLine()){
            System.out.println("Mam wiadomosc.");
            message = scanner.nextLine();
            System.out.println(message);
            System.out.println(message.split("[|]")[1]);
            if(message.split("[|]")[0].equals("UPDATEBOARD"))
            {
                System.out.println("Probuje uaktualnic plansze.");
                System.out.println(message.split("[|]")[1]);
                setBoard(message.split("[|]")[1]);
                //board.repaint();

                application.board = (DefaultBoard) this.board;
                application.repaint();
                application.board.repaint();
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

    public Board getBoard () {
        return this.board;
    }

    public void setBoard (String jsonBoard) {
    try {
        builder.setBoardFields(jsonBoard);
    } catch (JSONException e) {
        e.printStackTrace();
    }
    this.board = builder.getDefaultBoard();

}
}
