package board;

import socketClient.SocketClient;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class, stores data about
 * the owner, fields, players positions
 *
 */
public abstract class Board extends JPanel {
    public ArrayList<BoardField> fields = new ArrayList<>();
    private SocketClient player;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        for (BoardField field : this.fields) {
            g2d.setStroke(new BasicStroke(5));
            g2d.setPaint(field.getStatusColor_());
            g2d.draw(field);
            g2d.setPaint(field.getPlayerColor_());
            g2d.fill(field);
        }
    }

    /**
     * Sets the array list of board fields to what we want
     * @param fields the fields that will replace current ones
     *
     */
    abstract void setFields(ArrayList<BoardField> fields);

    public void setPlayer(SocketClient player) {
        this.player = player;
    }

    public SocketClient getPlayer() {
        return this.player;
    }
}
