package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DefaultBoard extends Board {
    private int mouseFlag = 0;

    public DefaultBoard () {
        this.isDoubleBuffered();
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setBackground(Color.DARK_GRAY);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getMouseFlag() == 0)
                for (BoardField field : fields) {
                    if (field.isHit(e.getX(),e.getY())
                            && getPlayer().getPlayerColor() == field.getPlayerColor()) {

                        field.setCurrentStatusColor(StatusColor.RED);
                        getPlayer().out.println("CURRENTFIELD|" + fields.indexOf(field));
                        repaint();
                        setMouseFlag(1);
                    }
                }
                if (getMouseFlag() == 1) {
                    for (BoardField field : fields) {
                        if (field.isHit(e.getX(),e.getY())
                                && field.getPlayerColor() == PlayerColor.NO_PLAYER) {

                            field.setCurrentStatusColor(StatusColor.RED);
                            getPlayer().out.println("MOVE|" + fields.indexOf(field));
                            repaint();
                            setMouseFlag(0);
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

    public int getMouseFlag() {
        return mouseFlag;
    }

    public void setMouseFlag(int i) {
        this.mouseFlag = i;
    }
}
