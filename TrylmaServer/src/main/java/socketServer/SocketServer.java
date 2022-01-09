package socketServer;

import Game.Game;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Defines a server's socket.
 * @author Małgorzata Orłowska
 * @see socketServer.TrylmaServer
 */
public class SocketServer {
    ServerSocket server = null;
    Game game;
	int numberOfPlayers;
    int port;
    public static List<Player> players;
	
    public SocketServer(int port, int numberOfPlayers) {
        System.out.println("Server");
        this.numberOfPlayers = numberOfPlayers;
        this.port = port;
        players = new ArrayList<>();

        try {
          server = new ServerSocket(port);
          System.out.println("The server is running.");
        }
        catch (IOException e) {
          System.out.println("Could not listen on port " + port);
          System.exit(-1);
        }
    }

    public void listenSocket() throws IOException {
        System.out.println("Waiting for connections with " + numberOfPlayers + " players.");
        ExecutorService executors = Executors.newFixedThreadPool(numberOfPlayers);

        int newPlayerId = 1;
        while (players.size() < numberOfPlayers) {
            Player player = new Player(server.accept(), newPlayerId);
            players.add(player);
            executors.execute(player);
            System.out.println("Player " + newPlayerId + " has connected.");
            newPlayerId++;
        }
        game = new Game(players);
    }

    @Override
    protected void finalize() {
        try {

          server.close();
        } catch (IOException e) {

          System.out.println("Could not close."); System.exit(-1);
        }
    }
}
