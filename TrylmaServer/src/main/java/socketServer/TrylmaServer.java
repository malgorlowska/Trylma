package socketServer;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 */
public class TrylmaServer 
{
    /*private static ExecutorService players;
    static int numberOfPlayers = 0;
    public static Game game;*/
    
	public static void main( String[] args )
    {
    	int port = 0; //port serwera
    	int numberOfPlayers = 0;
    	SocketServer server = null;

    	try {
    	port = Integer.parseInt(args[0]);
    	}
    	catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    	
    	try {
        numberOfPlayers = Integer.parseInt(args[1]);
    	}
    	catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    	
    	
			server = new SocketServer(port,numberOfPlayers);
		try {
			server.listenSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
