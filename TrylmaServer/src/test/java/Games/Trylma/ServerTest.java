package Games.Trylma;

import Board.JSONBoardConverter;
import Game.Game;
import org.junit.Test;
import socketServer.Player;
import socketServer.SocketServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertTrue;

public class ServerTest {
    @Test
    public void calculatingMove() throws IOException {
        SocketServer server = new SocketServer(4574,2);
        Player player1 = createMockedPlayer();
        Player player2 = createMockedPlayer();
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players);
        String boardBefore = Files.readString(Path.of("E:\\workspace\\Trylma\\TrylmaServer\\board1.txt"));
        String boardAfter = Files.readString(Path.of("E:\\workspace\\Trylma\\TrylmaServer\\board.txt"));

        assertTrue(game.getInitializationData(1).equals(boardBefore));

        game.move(7, 16, 1);
        JSONBoardConverter converter = new JSONBoardConverter();
        String updatedBoard = converter.buildJSONBoard(game.getBoard());
        assertTrue(boardAfter.equals(updatedBoard));
    }

    protected Player createMockedPlayer() {
        return mock(Player.class);

    }
}
