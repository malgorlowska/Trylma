package socketServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 */
public class Player implements Runnable
{
	int id;
	Socket socket;
	Scanner input;
	DataOutputStream outputStream;
	PrintWriter output;
    Vector<String> messages;
	
	public Player(Socket socket, int id, Vector<String> messages)
	{
		this.socket = socket;
		this.id = id;
        this.messages = messages;
	}

	 @Override
     public void run() {
         try {
             setup();
             outputStream.writeInt(id);

             while(input.hasNextLine()){
                 String message = input.nextLine();
                 System.out.println("Received message" + message + " from socket: " + socket);
                 String command = message.split("[|]")[0];
                 System.out.println("Read command: " + command);
                 if(command.equals("MESSAGE"))
                 {
                     System.out.println("Saving message in vector");
                     messages.add(message);
                 }

                else if(command.equals("GET"))
                 {
                     System.out.println("Sending back all messages");
                     output.println(messages);
                 }

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

     private void setup() throws IOException {
         input = new Scanner(socket.getInputStream());
         output = new PrintWriter(socket.getOutputStream(), true);
         outputStream = new DataOutputStream(socket.getOutputStream());
     }
     
}
