package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DefaultBoard extends Board {
    private int mouseFlag = 0;
    private boolean active = false;

    public DefaultBoard () {
        this.isDoubleBuffered();
        this.setOpaque(true);
        this.setBackground(new Color(76,81,109));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(active) {
                    if (getMouseFlag() == 0)
                        for (BoardField field : fields) {
                            if (field.isHit(e.getX(), e.getY())
                                    && getPlayer().getPlayerColor() == field.getPlayerColor()) {

                                field.setCurrentStatusColor(StatusColor.ACTIVE);
                                getPlayer().out.println("CHOSEN_FIELD|" + fields.indexOf(field));
                                repaint();
                                setMouseFlag(1);
                            }
                        }
                    if (getMouseFlag() == 1) {
                        for (BoardField field : fields) {
                            if (field.isHit(e.getX(), e.getY())
                                    && field.getPlayerColor() == PlayerColor.NO_PLAYER) {

                                field.setCurrentStatusColor(StatusColor.ACTIVE);
                                getPlayer().out.println("MOVE|" + fields.indexOf(field));
                                repaint();
                                setMouseFlag(0);
                            } else if(field.isHit(e.getX(), e.getY())
                                    && field.getPlayerColor() == field.getPlayerColor()) {

                                field.setCurrentStatusColor(StatusColor.ACTIVE);
                                getPlayer().out.println("CHOSEN_FIELD|" + fields.indexOf(field));
                                repaint();
                                setMouseFlag(1);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setFields(ArrayList<BoardField> fields) {
        this.fields = fields;
    }

    public void setActive(boolean active) {this.active = active;}

    public int getMouseFlag() {
        return mouseFlag;
    }

    public void setMouseFlag(int i) {
        this.mouseFlag = i;
    }
}
