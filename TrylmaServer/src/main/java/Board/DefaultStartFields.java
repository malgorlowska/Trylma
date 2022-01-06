package Board;

import java.util.ArrayList;

public class DefaultStartFields {
    public ArrayList<BoardField> topFields = new ArrayList<>();
    public ArrayList<BoardField> topLeftFields = new ArrayList<>();
    public ArrayList<BoardField> topRightFields = new ArrayList<>();
    public ArrayList<BoardField> bottomFields = new ArrayList<>();
    public ArrayList<BoardField> bottomLeftFields = new ArrayList<>();
    public ArrayList<BoardField> bottomRightFields = new ArrayList<>();


    public DefaultStartFields (DefaultBoard board){
        initTop(board);
        initTopLeft(board);
        initTopRight(board);
        initBottom(board);
        initBottomLeft(board);
        initBottomRight(board);
    }

    void initTop(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if (field.getRow() < 4) {
                this.topFields.add(field);
            }
        }
    }

    void initTopLeft(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if (field.getRow() == 4 && field.getColumn() < 4) {
                this.topLeftFields.add(field);
            }
            else if ((field.getRow() == 5 || field.getRow() == 6) && field.getColumn() < 3) {
                this.topLeftFields.add(field);
            }
            else if (field.getRow() == 7 && field.getColumn() < 2){
                this.topLeftFields.add(field);
            }
        }
    }

    void initTopRight(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if ((field.getRow() == 4 || field.getRow() == 5) && field.getColumn() > 8) {
                this.topRightFields.add(field);
            }
            else if ((field.getRow() == 6 || field.getRow() == 7) && field.getColumn() > 9) {
                this.topRightFields.add(field);
            }
        }
    }

    void initBottom(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if (field.getRow() > 12) {
                this.bottomFields.add(field);
            }
        }
    }

    void initBottomLeft(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if (field.getRow() == 12 && field.getColumn() < 4) {
                this.bottomLeftFields.add(field);
            }
            else if ((field.getRow() == 11 || field.getRow() == 10) && field.getColumn() < 3) {
                this.bottomLeftFields.add(field);
            }
            else if (field.getRow() == 9 && field.getColumn() < 2){
                this.bottomLeftFields.add(field);
            }
        }
    }

    void initBottomRight(DefaultBoard board) {
        for (BoardField field : board.fields) {
            if ((field.getRow() == 12 || field.getRow() == 11) && field.getColumn() > 8) {
                this.bottomRightFields.add(field);
            }
            else if ((field.getRow() == 10 || field.getRow() == 9) && field.getColumn() > 9) {
                this.bottomRightFields.add(field);
            }
        }
    }
}
