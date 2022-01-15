package Board;

import java.util.ArrayList;

/**
 * The game board stores data about
 * the fields and players positions
 *
 */
public class DefaultBoard extends Board {

    @Override
    public void setFields(ArrayList<BoardField> fields) {
        this.fields = fields;
    }

    @Override
    void setStartFields(DefaultStartFields startFields) {
        this.startFields = startFields;
    }
}
