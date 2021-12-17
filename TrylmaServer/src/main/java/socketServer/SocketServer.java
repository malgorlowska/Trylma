package socketServer;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Defines a server's socket.
 * @author Małgorzata Orłowska
 * @see socketServer.TrylmaServer
 */
public class SocketServer
{
	ServerSocket server = null;
	int numberOfPlayers;

	private Vector<Player> players = null; //lista graczy?
	//Socket client = null;
	//BufferedReader in = null;
	//PrintWriter out = null;
	//String line = ""; //server zczytuje po linii
	
	//Vector<String> messages = new Vector<>(); //wektor wszystkich komend przesłanych do serwera
	//FileOutputStream file;
	//ObjectOutputStream objectOutput = null;
	
  /**
   * Basic constructor.
 * @param numberOfPlayers 
 * @param port 
   */
  SocketServer(int port, int numberOfPlayers)
  { 
	System.out.println("Server");
	this.numberOfPlayers = numberOfPlayers;
	players = new Vector<Player>();
    try 
    {
    	
      server = new ServerSocket(port); 
      //file = new FileOutputStream("file.txt");

      // Creates an ObjectOutputStream
      //objectOutput = new ObjectOutputStream(file);
    } 
    catch (IOException e) 
    {
      System.out.println("Could not listen on port 4444"); System.exit(-1);
    }
  }

  /**
   * Reading input from socket 4444.
   */
  public void listenSocket() 
  {
    try 
    {
    	System.out.println("Waiting for connections with " + numberOfPlayers + " players.");
    	for( int i = 0; i < numberOfPlayers; i++)
    	{
    		Socket s = server.accept();
    		//clients.add(new Player(server.accept(), i));
    		System.out.println("Player " + i + " has connected.");
    		Player player = new Player(s, i+1);
    		players.add(player);
    		System.out.println("Dodałam nowego gracza do polaczen serwera.");
    		Thread t = new Thread(player);
			//System.out.println("utworzylam nowy watek");
			t.start();
    	}
      //client = server.accept();
    } 
    catch (IOException e) 
    {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
    
    /*try 
    {
      // Receiving from a socket.
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      // Sending to the socket(string)
      out = new PrintWriter(client.getOutputStream(), true);
      // Sending to the socket(object)
      objectOutput = new ObjectOutputStream(client.getOutputStream());
      
    } 
    catch (IOException e) 
    {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
    
    while(line != null) 
    {
      try 
        {
            // Strings received from a socket.
          line = in.readLine();
          // Print to server
          System.out.println(line);       
          
          if(line.equals("GET"));
          {
        	  out.println("Server has already read your message: " + line); 
              //objectOutput.writeObject(line);	  
          }
          
          //messages.add(line);     
          //objectOutput.writeObject(messages);
          //objectOutput.close();
        
        } 
        catch (IOException e) 
        {
          System.out.println("Read failed"); System.exit(-1);
        } 
    }*/
  }

  /**
   * Closing the sockets.
   */
  @Override
  protected void finalize() 
  {
    try 
    {
      //in.close();
      //out.close();
      //client.close();
      server.close();
    } 
    catch (IOException e) 
    {
      System.out.println("Could not close."); System.exit(-1);
    }
  }
        
    
}
