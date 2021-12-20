package board;

import socketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;

public abstract class Board extends JPanel {
    public ArrayList<BoardField> fields = new ArrayList<>();
    private SocketClient player;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        for (BoardField field : this.fields) {
            g2d.setStroke(new BasicStroke(5));
            g2d.setPaint(field.getCurrentStatusColor());
            g2d.draw(field);
            g2d.setPaint(field.getCurrentPlayerColor());
            g2d.fill(field);
        }
    }

    abstract void setFields(ArrayList<BoardField> fields);

    public void setPlayer(SocketClient client) {
        this.player = player;
    }

    public SocketClient getPlayer() {
        return this.player;
    }
}
