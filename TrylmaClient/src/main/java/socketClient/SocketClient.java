package socketClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class SocketClient 
{
	int playerId;
	Socket socket = null;
    public PrintWriter out = null;
    public BufferedReader in = null;
    public DataInputStream input = null;
    public ObjectInputStream objectInput = null;
    
    int port = 0;
    
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
        try 
        {
        	System.out.println("otwieram socketa");
          socket = new Socket("localhost", port);
          // Connecting with socket
          // Sending to the server
          System.out.println("printwriter");
          out = new PrintWriter(socket.getOutputStream(), true);
          // Receiving strings from server
          System.out.println("buffered reader");
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          input = new DataInputStream(socket.getInputStream());
        //Receiving objects from server
          //FileInputStream fileStream = new FileInputStream("file.txt");
          //ObjectInputStream objStream = new ObjectInputStream(fileStream);
          System.out.println("object input stream");
         // objectInput = new ObjectInputStream(socket.getInputStream());
          System.out.println("koniec otwierania socketa");
          //playerId = Integer.parseInt(in.readLine()); // try catch powinien byc
          playerId = input.readInt();
          System.out.println("My id is " + playerId);
        }
        catch (UnknownHostException e) 
        {
           System.out.println("Unknown host: localhost"); System.exit(1);
        }
         catch  (IOException e) 
        {
           System.out.println("No I/O"); System.exit(1);
           System.out.println(e.getStackTrace());
        }
    }

}
