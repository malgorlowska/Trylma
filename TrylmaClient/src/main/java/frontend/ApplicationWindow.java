package frontend;

import javax.swing.JFrame;

import board.*;
import org.json.JSONException;
import java.awt.*;

public class ApplicationWindow extends JFrame {

    int playerID;
    DefaultBoardBuilder builder;
    PlayerInfoPanel infoPanel;
    PlayerColor playerColor;
    public DefaultBoard board;

	/**
     *  Basic constructor.
	 * @param playerID player who runs this app
     *
     * */
    public ApplicationWindow(int playerID, int currentPlayerID, String JSONBoard) {
    	this.playerID = playerID;
        this.playerColor = PlayerColor.fromInteger(playerID);
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

    public void setCurrPlayer(int currPlayerID) {
        PlayerColor currPlayerColor = PlayerColor.fromInteger(currPlayerID);
        ifYouCurrentPlayer(currPlayerID);
        this.infoPanel.setCurrentPlayerInfo(currPlayerColor);
    }

    public void ifYouCurrentPlayer(int currPlayer)
    {
        if(this.playerID == currPlayer)
            this.board.setActive(true);
        else
            this.board.setActive(false);
    }

    public Board getBoard () {
        return this.board;
    }

    public void setBoard (String jsonBoard) {
        try {
            builder.setBoardFields(jsonBoard);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.board = builder.getDefaultBoard();
    }
}
