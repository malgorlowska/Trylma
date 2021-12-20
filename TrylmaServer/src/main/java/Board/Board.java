package Board;

import java.util.ArrayList;

    public abstract class Board {
        public ArrayList<BoardField> fields = new ArrayList<>();
        public DefaultStartFields startFields;

        abstract void setFields(ArrayList<BoardField> fields);
        abstract void setStartFields();
    }

