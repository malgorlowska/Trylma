package frontend;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import socketClient.SocketClient;

public class ApplicationWindow extends JFrame{
	
	JPanel buttonPanel;
	ButtonsBox theBox;
	SocketClient client; //potrzebne??
	/** Basic constructor. 
	 * @param client */
	
    public ApplicationWindow(SocketClient client)
    {
    	this.client = client; //potrzebne??
        int width=800;
        int height=600;
        this.setSize(width, height);
        this.setTitle("Trylma");       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buttonPanel = new JPanel();
        theBox = new ButtonsBox(this);
        
        buttonPanel.add(theBox);
        this.add(buttonPanel, BorderLayout.SOUTH);
       
    }

}
