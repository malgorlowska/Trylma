package Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

    public abstract class Board {
        public ArrayList<BoardField> fields = new ArrayList<>();

        abstract void setFields(ArrayList<BoardField> fields);
    }

