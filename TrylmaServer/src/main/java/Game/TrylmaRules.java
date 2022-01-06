package Game;

import Board.*;
import java.util.ArrayList;

public class TrylmaRules implements Rules {
    private ArrayList<Integer> availableFields = new ArrayList<>();

    @Override
    public boolean isWinner(Board board, int playerID) {
        return false;
    }

    @Override
    public boolean moveIsCorrect(Board board, int endMoveField) {
        return availableFields.contains(endMoveField);
    }

    @Override
    public void setAvailableFields(Board board, int startMoveField, boolean isFirstCheck) {
        BoardField startField = board.fields.get(startMoveField);
        ArrayList<BoardField> neighbors = this.neighbors(board, startField);

        for (BoardField field : neighbors) {
            if (field.getPlayerColor() == PlayerColor.NO_PLAYER && isFirstCheck){

                this.availableFields.add(board.fields.indexOf(field));
            }
            else if (field.getPlayerColor() != PlayerColor.NO_PLAYER) {

                this.tryToJump(board, startField, field);
            }
        }
    }

    public ArrayList<BoardField> neighbors(Board board, BoardField currField) {
        ArrayList<BoardField> neighbors = new ArrayList<>();
        int currRow = currField.getRow();
        int currColumn = currField.getColumn();

        for (BoardField field : board.fields) {
            int row = field.getRow();
            int column = field.getColumn();

            if ((row == currRow) && ((column == currColumn + 1) || (column == currColumn - 1))) {

                neighbors.add(field);
            }

            if (row % 2 == 0) {
                if (((row == currRow - 1) || (row == currRow + 1)) && (column == currColumn - 1)) {

                    neighbors.add(field);
                }
            } else if (row % 2 == 1) {
                if (((row == currRow - 1) || (row == currRow + 1)) && (column == currColumn + 1)) {

                    neighbors.add(field);
                }
            }
        }
        return neighbors;
    }

    public void tryToJump(Board board, BoardField currField, BoardField fieldToPass) {
        ArrayList<BoardField> passFieldNeighbors = this.neighbors(board, fieldToPass);

        if (currField.getRow() == fieldToPass.getRow()) {
            for (BoardField field_ : passFieldNeighbors) {
                if (field_.getPlayerColor() == PlayerColor.NO_PLAYER) {
                    if (!field_.equals(currField) && field_.getRow() == currField.getRow()) {

                        this.availableFields.add(board.fields.indexOf(field_));
                        this.setAvailableFields(board, board.fields.indexOf(field_), false);
                    }
                }
            }
        }
        else {
            for (BoardField field_ : passFieldNeighbors) {
                if (field_.getPlayerColor() == PlayerColor.NO_PLAYER) {
                    if (!field_.equals(currField) &&
                            field_.getRow() != fieldToPass.getRow() &&
                            field_.getRow() != currField.getRow() &&
                            field_.getColumn() != currField.getColumn()) {

                        this.availableFields.add(board.fields.indexOf(field_));
                        this.setAvailableFields(board, board.fields.indexOf(field_), false);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getAvailableFields() {
        return this.availableFields;
    }
}
