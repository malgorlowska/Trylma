package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
                        getPlayer().out.println(fields.indexOf(field));
                        mouseFlag ++;
                    }
                }
                if (getMouseFlag() == 1) {
                    for (BoardField field : fields) {
                        if (field.isHit(e.getX(),e.getY())
                                && getPlayer().getPlayerColor() == PlayerColor.NO_PLAYER) {

                            field.setCurrentStatusColor(StatusColor.RED);
                            getPlayer().out.println(fields.indexOf(field));
                            String serverBoardUpdate = "";
                            try {
                                serverBoardUpdate = getPlayer().in.readLine();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            getPlayer().setBoard(serverBoardUpdate);
                            resetMouseFlag();
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

    public void resetMouseFlag() {
        this.mouseFlag = 0;
    }
}
