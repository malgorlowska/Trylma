package board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Board extends JPanel {
    public ArrayList<BoardField> fields = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //super.paint(g2d);
        for (BoardField field : this.fields) {
            g2d.setStroke(new BasicStroke(7));
            g2d.setPaint(field.getCurrentStatusColor());
            g2d.draw(field);
            g2d.setPaint(field.getCurrentPlayerColor());
            g2d.fill(field);
        }
    }

    abstract void setFields(ArrayList<BoardField> fields);
}
