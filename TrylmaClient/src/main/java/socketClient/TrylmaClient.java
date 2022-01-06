package socketClient;

import frontend.ApplicationWindow;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class TrylmaClient 
{
    public static void main( String[] args ) throws IOException {
    	if (args.length != 1) 
    	{
            System.err.println("Pass the server IP as command line argument.");
            return;
        }
    	int port = 0;
    	try {
    		port = Integer.parseInt(args[0]);
    	}
    	catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    	
    	SocketClient client = new SocketClient(port);
    	client.listenSocket();
		client.play();
    }
}
