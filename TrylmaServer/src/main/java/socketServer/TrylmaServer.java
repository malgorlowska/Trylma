package socketServer;

import java.io.IOException;

/**
 * 
 *
 */
public class TrylmaServer {
    
	public static void main( String[] args ) throws Exception {
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

		int[] players = {2, 3, 4, 6};
		boolean validNumberOfPlayers = false;

		for(int p : players){
			if (numberOfPlayers == p) {
				validNumberOfPlayers = true;
				break;
			}
		}

		if(!validNumberOfPlayers) {
			throw new Exception("Please choose 2, 3, 4 or 6 as number of players.");
		}

		server = new SocketServer(port,numberOfPlayers);
		try {
			server.listenSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
