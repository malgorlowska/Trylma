package frontend;

import javax.swing.JFrame;

import board.DefaultBoard;
import board.PlayerColor;
import socketClient.SocketClient;
import java.awt.*;

public class ApplicationWindow extends JFrame{
	
	//JPanel buttonPanel;
	//ButtonsBox theBox;

	SocketClient player; //potrzebne??
    PlayerInfoPanel infoPanel;
    PlayerColor playerColor;
    public DefaultBoard board;

	/** Basic constructor. 
	 * @param player player who runs this app*/
	
    public ApplicationWindow(SocketClient player) {
    	this.player = player;
        this.playerColor = this.player.getPlayerColor();
        this.infoPanel = new PlayerInfoPanel(playerColor.toString());
        this.board = (DefaultBoard)player.getBoard();
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.WEST);
        this.add(board, BorderLayout.CENTER);


        int width = 800;
        int height = 600;
        this.setSize(width, height);
        this.setTitle("Trylma");       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //buttonPanel = new JPanel();
        //theBox = new ButtonsBox(this);
        
        //buttonPanel.add(theBox);
        //this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
