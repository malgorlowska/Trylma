package socketServer;

import java.io.DataOutputStream;
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
public class Player implements Runnable
{
	int id;
	Socket socket;
	Scanner input;
	DataOutputStream outputStream;
	PrintWriter output;
	
	public Player(Socket socket, int id)
	{
		this.socket = socket;
		this.id = id;
	}
	
	 @Override
     public void run() {
         try {
             setup();
             System.out.println("Moje id to " + id);
           // output.print(id); 
            outputStream.writeInt(id);
            System.out.println("Wysłałam id");
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
