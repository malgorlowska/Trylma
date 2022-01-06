package Game;

import Board.*;
import java.util.ArrayList;

public class TrylmaRules implements Rules {
    private ArrayList<Integer> availableFields = new ArrayList<>();

    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public boolean moveIsCorrect(Board board, BoardField endMoveField) {
        return availableFields.contains(board.fields.indexOf(endMoveField));
    }


    public void setAvailableFields(Board board, int startMoveField) {
        BoardField startField = board.fields.get(startMoveField);
        int currRow = startField.getRow();
        int currColumn = startField.getColumn();

        for (BoardField field : board.fields) {
            int row = field.getRow();
            int column = field.getColumn();

            if (field.getPlayerColor() == PlayerColor.NO_PLAYER) {

                if ((row == currRow) && ((column == currColumn + 1) || (column == currColumn - 1))) {

                    this.availableFields.add(board.fields.indexOf(field));
                }

                if (row % 2 == 0) {
                    if (((row == currRow - 1) || (row == currRow + 1)) && (column == currColumn - 1)) {

                        this.availableFields.add(board.fields.indexOf(field));
                    }
                } else if (row % 2 == 1) {
                    if (((row == currRow - 1) || (row == currRow + 1)) && (column == currColumn + 1)) {

                        this.availableFields.add(board.fields.indexOf(field));
                    }
                }
            }
            else if (field.getPlayerColor() != PlayerColor.NO_PLAYER){
                this.setAvailableFields(board, board.fields.indexOf(field));
            }
        }
    }

    public void addAvailableField(int fieldIndex) {
        this.availableFields.add(fieldIndex);
    }

}
