package socketClient;

import frontend.ApplicationWindow;

/**
 * Hello world!
 *
 */
public class TrylmaClient 
{
    public static void main( String[] args )
    {
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

    	ApplicationWindow application = new ApplicationWindow(client);
    	application.setVisible(true);   	
    }
}