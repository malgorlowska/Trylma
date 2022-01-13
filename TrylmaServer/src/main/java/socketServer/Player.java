package socketServer;

import Board.JSONBoardConverter;
import Game.Game;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 *
 */
public class Player implements Runnable {
    Game game;
    Socket socket;
	Scanner input;
	DataOutputStream outputStream;
	public PrintWriter output;
    int playerId;
    int startMoveField;
    int endMoveField;
	
	public Player(Socket socket, int playerId) {
		this.socket = socket;
        this.playerId = playerId;

        try {
            this.setup();
        } catch (IOException e) {
            System.out.println("Player creating error");
        }

    }

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        outputStream = new DataOutputStream(socket.getOutputStream());
    }


	 @Override
     public void run() {
         try {
             while (input.hasNextLine()) {
                 String message = input.nextLine();
                 String command = message.split("[|]")[0];
                 System.out.println("komenda " + command);
                 String receivedData = message.split("[|]")[1];

                 switch (command) {
                     case "QUIT" : {
                         System.out.println(playerId + "has left the game");
                         return;
                     }
                     case "CHOSEN_FIELD" : {
                         System.out.println("player " + playerId + " current field");
                         System.out.println("chosen fields, board " + receivedData);
                         JSONBoardConverter converter = new JSONBoardConverter();
                         String updatedBoard = converter.buildJSONBoard(game.getBoard());
                         System.out.println(updatedBoard);
                         startMoveField = Integer.parseInt(receivedData);
                         game.showPossibilities(startMoveField, playerId);
                         ArrayList<Integer> available = game.getRules().getAvailableFields();
                         System.out.println("available " + available);
                         break;
                     }
                     case "MOVE" : {
                         System.out.println("player " + playerId + "is trying to move");
                         endMoveField = Integer.parseInt(receivedData);
                         game.move(startMoveField, endMoveField, playerId);
                         System.out.println("startMoveField " + startMoveField + " endMoveField " + endMoveField);
                         break;
                     }
                     case "RESET_ACTION" : {

                         break;
                     }
                 }

             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         finally {
             try {
                 socket.close();
             } catch (IOException e) {
                 System.out.println("error in Player");
             }
         }
     }


     public void setGame(Game game) {
        this.game = game;
     }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public void sendMessage(String message) {
        System.out.println("trying to sent message: " + message);
        output.println(message);
    }
}
