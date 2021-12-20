package socketServer;

import Game.Game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * 
 *
 */
public class Player implements Runnable {
	Socket socket;
	Scanner input;
	DataOutputStream outputStream;
	public PrintWriter output;
    int id;
    Game game;
    Vector<String> messages; //zbędne
	
	public Player(Socket socket, int id, Vector<String> messages) {
		this.socket = socket;
        this.messages = messages;
        this.id = id;

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
                 System.out.println("Received message" + message + " from socket: " + socket);
                 String command = message.split("[|]")[0];
                 System.out.println("Read command: " + command);
                 if (command.equals("QUIT")) {
                     System.out.println(id + "has left the game");
                     messages.add(message);//zbędne
                     return;
                 }
                 else if (command.equals("MOVE")) {
                     System.out.println("player " + id + "is trying to move");
                     messages.add(message);//zbędne
                     moveCommand(message.split("[|]")[1]);//Integer.parseInt(command.substring(5)));
                     game.sendToPlayers(message.split("[|]")[1]);
                 }

                /*else if(command.equals("GET")) {
                     System.out.println("Sending back all messages");
                     output.println(messages);
                 }*/

             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 socket.close();
             } catch (IOException e) {
             }
         }
     }

     private void moveCommand(String command)
     {
         game.move(command, id);
     }

     public void setGame(Game g){
        this.game = g;
     }

    public int getId(){
        return id;
    }

    public void sendMessage(String message) {
        System.out.println("probuje wyslac wiadomosc: " + message);
        output.println(message);
    }
}
