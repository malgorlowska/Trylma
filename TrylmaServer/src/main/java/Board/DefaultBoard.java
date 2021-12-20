package Board;

import java.util.ArrayList;

public class DefaultBoard extends Board {

    public DefaultBoard() {

    }

    @Override
    public void setFields(ArrayList<BoardField> fields) {
        this.fields = fields;
    }

    @Override
    void setStartFields() {
        this.startFields = new DefaultStartFields(this);
    }

}
