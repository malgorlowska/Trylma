package socketServer;

import java.io.IOException;

/**
 * 
 *
 */
public class TrylmaServer {
    
	public static void main( String[] args ) {
    	int port = 0;
    	int numberOfPlayers = 0;
    	SocketServer server;

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
