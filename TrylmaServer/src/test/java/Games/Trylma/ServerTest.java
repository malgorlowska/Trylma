package Games.Trylma;

import Board.BoardField;
import Board.JSONBoardConverter;
import Board.PlayerColor;
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
        SocketServer server = new SocketServer(4574,2, 2);
        Player player1 = createMockedPlayer();
        Player player2 = createMockedPlayer();
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, 2);

        String validBoardBefore = Files.readString(Path.of("E:\\workspace\\Trylma\\TrylmaServer\\board1.txt"));
        validBoardBefore = validBoardBefore.replaceAll("\\s+","");

        String validBoardAfter = Files.readString(Path.of("E:\\workspace\\Trylma\\TrylmaServer\\board.txt"));
        validBoardAfter = validBoardAfter.replaceAll("\\s+","");

        String initializationData = game.getInitializationData(1);
        initializationData = initializationData.replaceAll("\\s+","");
        assertTrue(validBoardBefore.equals(initializationData));

        game.move(7, 16, 1);
        JSONBoardConverter converter = new JSONBoardConverter();
        String updatedBoard = converter.buildJSONBoard(game.getBoard());
        System.out.println(updatedBoard);

        assertTrue(validBoardAfter.equals(updatedBoard));
    }
    @Test
    public void whoWinTheGame(){
        SocketServer server = new SocketServer(4576,3, 2);
        Player player1 = createMockedPlayer();
        Player player2 = createMockedPlayer();
        Player player3 = createMockedPlayer();
        List<Player> players = new ArrayList<>();

        when(player1.getPlayerId()).thenReturn(1);
        when(player2.getPlayerId()).thenReturn(2);
        when(player3.getPlayerId()).thenReturn(3);

        players.add(player1);
        players.add(player2);
        players.add(player3);

        Game game = new Game(players, 2);

        for(BoardField f:  game.getBoard().startFields.bottomFields)
            f.setPlayerColor(PlayerColor.fromInteger(player1.getPlayerId()));

        assertTrue(!game.getRules().isWinner(game,player1.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player2.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player3.getPlayerId()));

        for(BoardField f:  game.getBoard().startFields.bottomFields)
            f.setPlayerColor(PlayerColor.fromInteger(player2.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player1.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player2.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player3.getPlayerId()));

        for(BoardField f:  game.getBoard().startFields.bottomFields)
            f.setPlayerColor(PlayerColor.fromInteger(player3.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player1.getPlayerId()));
        assertTrue(!game.getRules().isWinner(game,player2.getPlayerId()));
        assertTrue(game.getRules().isWinner(game,player3.getPlayerId()));
    }

    @Test
    public void ifPlayerCouldMove() throws IOException {
        SocketServer server = new SocketServer(4577,3, 2);
        Player player1 = createMockedPlayer();
        Player player2 = createMockedPlayer();
        Player player3 = createMockedPlayer();
        List<Player> players = new ArrayList<>();

        when(player1.getPlayerId()).thenReturn(1);
        when(player2.getPlayerId()).thenReturn(2);
        when(player3.getPlayerId()).thenReturn(3);

        players.add(player1);
        players.add(player2);
        players.add(player3);

        Game game = new Game(players, 2);

        game.setCurrentPlayer(2);
        try{
            game.move(2, 5, 1);
            assertTrue(1 == 0); //if we reach this assert that means game.move did not break, so - allowed illegal move
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setAvailableFields() throws IOException {
        SocketServer server = new SocketServer(4578,3, 2);
        Player player1 = createMockedPlayer();
        Player player2 = createMockedPlayer();
        Player player3 = createMockedPlayer();
        List<Player> players = new ArrayList<>();

        when(player1.getPlayerId()).thenReturn(1);
        when(player2.getPlayerId()).thenReturn(2);
        when(player3.getPlayerId()).thenReturn(3);

        players.add(player1);
        players.add(player2);
        players.add(player3);

        Game game = new Game(players, 2);

        String boardBefore = Files.readString(Path.of("E:\\workspace\\Trylma\\TrylmaServer\\boardBeforeSetAvailableFields.txt"));
        boardBefore = boardBefore.replaceAll("\\s+","");

        game.setCurrentPlayer(2);
        game.showPossibilities(44,2);
        ArrayList<Integer> availableFields= game.getRules().getAvailableFields();
        assertTrue(availableFields.size() == 2);
        assertTrue(availableFields.get(0) == 43);
        assertTrue(availableFields.get(1) == 54);
    }


    protected Player createMockedPlayer() {
        return mock(Player.class);
    }
}
