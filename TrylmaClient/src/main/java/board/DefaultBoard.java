package board;

import socketClient.SocketClient;

import java.util.ArrayList;

public class DefaultBoard extends Board {

    public DefaultBoard () {
        this.isDoubleBuffered();
        this.setOpaque(true);
    }

    @Override
    public void setFields(ArrayList<BoardField> fields) {
        this.fields = fields;
    }

}
