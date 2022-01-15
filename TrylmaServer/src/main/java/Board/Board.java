package Board;

import java.util.ArrayList;

/**
 * Abstract class, stores data about
 * board - fields and players positions
 *
 */
public abstract class Board {
    public ArrayList<BoardField> fields = new ArrayList<>();
    public DefaultStartFields startFields;

    /**
     * Sets the array list of board fields
     * @param fields the fields that will replace current ones
     *
     */
     abstract void setFields(ArrayList<BoardField> fields);

    /**
     * Sets the start fields of the board
     * @param startFields start fields of the board
     *
     */
    abstract void setStartFields(DefaultStartFields startFields);


}