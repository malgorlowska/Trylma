package frontend;

import java.awt.TextField;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ButtonsBox extends Box{
	
    TextField forKey;
    final JButton addMessage;
    final JButton showMessages;
    ApplicationWindow app;
    
    
    /**
     * Basic constructor
     * @param app application
     */
    public ButtonsBox(ApplicationWindow app)
    {
        super(BoxLayout.LINE_AXIS);
        this.app = app;
        
        forKey = new TextField(10);
        this.add(forKey);
        
        addMessage = new JButton("Add a message");
        showMessages = new JButton("Show all messages");

        addButton(this, addMessage, (ActionEvent event) -> 
        {
            // Sending to the server
            if(forKey.getText() != null) 
            {
            	app.player.out.println("MESSAGE|".concat(forKey.getText()));
                    //board.repaint();
                
                forKey.setText("");
                forKey.requestFocus();
            }
        });  //END ADD_MESSAGE
        
        addButton(this, showMessages, (ActionEvent event) -> 
        {
            	app.player.out.println("GET|");

					try {
						System.out.println(app.player.in.readLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            
        });  //END SHOW_MESSAGES

    }
    
    /**
    * Adds a button to a container and sets its ActionListener
    * @param c buttonPanel
    * @param button button to be added
    * @param listener listener of the button
    */
    public void addButton(Container c, AbstractButton button, 
            ActionListener listener)
    {
        c.add(button);
        button.addActionListener(listener);
        button.setEnabled(true); //false
    }
    
    public void disabledButtons()
    {
        addMessage.setEnabled(false);
    }
    
 
}
