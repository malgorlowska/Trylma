package socketClient;

import board.*;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class SocketClient {
	private int playerId;
    private PlayerColor playerColor;
    private Board board;
    public PrintWriter out = null;
    public BufferedReader in = null;
    public Scanner scanner = null;
    public DataInputStream input = null;
    DefaultBoardBuilder builder = null;
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
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          input = new DataInputStream(socket.getInputStream());

          String message = in.readLine();
          String playerID = message.split("[|]")[0];
          String JSONBoard = message.split("[|]")[1];

          setPlayerId(Integer.parseInt(playerID));
          setPlayerColor(PlayerColor.fromInteger(getPlayerId()));

          builder = new DefaultBoardBuilder();
          setBoard(JSONBoard);
          this.board.setPlayer(this);

          scanner = new Scanner(socket.getInputStream());

          System.out.println("My id is " + getPlayerId());
          System.out.println("My color is " + getPlayerColor().toString());
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
            String command = message.split("[|]")[0];

            if (command.equals("UPDATE_BOARD")) {
                System.out.println("updating the board ...");
                String updatedBoard = message.split("[|]")[1];
                setBoard(updatedBoard);
                this.getBoard().repaint();
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
