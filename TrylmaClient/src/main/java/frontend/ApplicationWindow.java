package frontend;

import javax.swing.JFrame;
import board.*;
import org.json.JSONException;
import java.awt.*;

/**
 * Displays and stores information about
 * board, which player are we, who is current player.
 * Main app window.
 *
 */
public class ApplicationWindow extends JFrame {

    DefaultBoardBuilder builder;
    PlayerInfoPanel infoPanel;
    PlayerColor playerColor;
    public DefaultBoard board;

    /**
     * Basic Constructor
     *
     * @param playerColor the color of our fields
     * @param currentPlayerID  id of a current player
     * @param JSONBoard json board received from server
     *
     */
    public ApplicationWindow(PlayerColor playerColor, int currentPlayerID, String JSONBoard) {
        this.playerColor = playerColor;
        this.infoPanel = new PlayerInfoPanel(playerColor);

        this.builder = new DefaultBoardBuilder();
        this.setBoard(JSONBoard);
        this.setCurrPlayer(currentPlayerID);

        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.WEST);
        this.add(board, BorderLayout.CENTER);

        int width = 825;
        int height = 650;
        this.setSize(width, height);
        this.setTitle("TRYLMA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Changes data and displayed info about current player
     *
     * @param currPlayerID id of a current player
     *
     */
    public void setCurrPlayer(int currPlayerID) {
        PlayerColor currPlayerColor = PlayerColor.fromInteger(currPlayerID);
        ifYouCurrentPlayer(currPlayerID);
        this.infoPanel.setCurrentPlayerInfo(currPlayerColor);
    }

    /**
     * Gives us ability to make a move
     * if we are the current player
     *
     * @param currPlayer id of a current player
     *
     */
    public void ifYouCurrentPlayer(int currPlayer) {
        if(this.playerColor == PlayerColor.fromInteger(currPlayer))
            this.board.setActive(true);
        else
            this.board.setActive(false);
    }

    /**
     * @return returns app board
     *
     */
    public Board getBoard () {
        return this.board;
    }

    /**
     * Sets and refreshes player board based on
     * information from the server
     *
     * @param jsonBoard json string with board from server
     *
     */
    public void setBoard (String jsonBoard) {
        try {
            builder.setBoardFields(jsonBoard);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.board = builder.getDefaultBoard();
    }
}
