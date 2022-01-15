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
 * Accepts players.
 *
 */
public class SocketServer {
    public static List<Player> players;
    ServerSocket server = null;
    Game game;
	int numberOfPlayers;
    int boardSize;
    int port;


    /**
     * Basic constructor
     * @param port server port
     * @param numberOfPlayers amount of players
     * @param boardSize size of game board
     *
     */
    public SocketServer(int port, int numberOfPlayers, int boardSize) {
        System.out.println("Server");
        this.numberOfPlayers = numberOfPlayers;
        this.boardSize = boardSize;
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

    /**
     * Waits for players to join the server
     * When all joined, starts the game
     * @throws IOException exception
     *
     */
    public void listenSocket() throws IOException {
        System.out.println("Waiting for connections with " + numberOfPlayers + " players.");
        ExecutorService executors = Executors.newFixedThreadPool(numberOfPlayers);

        int newPlayerId = 1;
        while (players.size() < numberOfPlayers) {
            Player player = new Player(server.accept(), newPlayerId);
            players.add(player);
            executors.execute(player);
            System.out.println("Player " + newPlayerId + " has connected.");
            newPlayerId ++;
        }
        game = new Game(players, boardSize);
    }
}
