package socketClient;

/**
 * Client class that sets the socket
 * and runs the app.
 * It needs server port passed as an argument.
 *
 */
public class TrylmaClient {

	/**
	 * Runs the app
	 * Server port must be passed as an argument
	 * @param args you have to pass the server port
	 *
	 */
    public static void main( String[] args ) {
    	if (args.length != 1) {
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
