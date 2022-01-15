package board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The game board stores data about
 * the board owner and players positions
 * it also have mouse listener that sends
 * info to the server when making a move
 *
 */
public class DefaultBoard extends Board {
    private int mouseFlag = 0;
    private boolean active = false;

    /**
     * Sets board background and ads mouse listener.
     * Handles sending data about a move to the server
     *
     */
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

                                field.setStatusColor_(StatusColor.ACTIVE);
                                getPlayer().out.println("CHOSEN_FIELD|" + fields.indexOf(field));
                                repaint();
                                setMouseFlag(1);
                            }
                        }
                    if (getMouseFlag() == 1) {
                        for (BoardField field : fields) {
                            if (field.isHit(e.getX(), e.getY())
                                    && field.getPlayerColor() == PlayerColor.NO_PLAYER) {

                                field.setStatusColor_(StatusColor.ACTIVE);
                                getPlayer().out.println("MOVE|" + fields.indexOf(field));
                                repaint();
                                setMouseFlag(0);
                            }
                            else if(field.isHit(e.getX(), e.getY())
                                    && field.getPlayerColor() == field.getPlayerColor()) {

                                field.setStatusColor_(StatusColor.ACTIVE);
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

    /**
     * Determines if player can make a move
     * @param active if true - can move
     * @see frontend.ApplicationWindow
     *
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gives info about what mouse click was made
     * @return current mouse flag
     *
     */
    public int getMouseFlag() {
        return mouseFlag;
    }

    /**
     * Sets mouse flag
     * @param i new mouse flag
     *
     */
    public void setMouseFlag(int i) {
        this.mouseFlag = i;
    }
}
