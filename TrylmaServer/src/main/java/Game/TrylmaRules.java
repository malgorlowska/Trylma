package Game;

import Board.*;

import java.util.ArrayList;

public class TrylmaRules implements Rules {
    private ArrayList<Integer> availableFields = new ArrayList<>();
    private int playersCount;

    public TrylmaRules(int playersCount) {
        this.playersCount = playersCount;
    }

    public boolean isStartingFieldInTargetTriangle(Board board, int startMoveField, int currentPlayer) {
        BoardField startField = board.fields.get(startMoveField);
        int currRow = startField.getRow();
        int currColumn = startField.getColumn();
        switch (playersCount) {
            case 2: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else {
                    for (BoardField field : board.startFields.topFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }
                break;
            }

            case 3: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else {
                    for (BoardField field : board.startFields.bottomFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }
                break;
            }

            case 4: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else if (currentPlayer == 3){
                    for (BoardField field : board.startFields.topRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else {
                    for (BoardField field : board.startFields.topLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }
                break;
            }

            case 6: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else if (currentPlayer == 3){
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else if (currentPlayer == 4){
                    for (BoardField field : board.startFields.topRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else if (currentPlayer == 5){
                    for (BoardField field : board.startFields.topLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }else {
                    for (BoardField field : board.startFields.topFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean isWinner(Board board, int currentPlayer) {
        switch (playersCount) {
            case 2: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                } else {
                    for (BoardField field : board.startFields.topFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }
                break;
            }

            case 3: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else {
                    for (BoardField field : board.startFields.bottomFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }
                break;
            }

            case 4: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else if (currentPlayer == 3){
                    for (BoardField field : board.startFields.topRightFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else {
                    for (BoardField field : board.startFields.topLeftFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }
                break;
            }

            case 6: {
                if (currentPlayer == 1) {
                    for (BoardField field : board.startFields.bottomFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : board.startFields.bottomRightFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else if (currentPlayer == 3){
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else if (currentPlayer == 4){
                    for (BoardField field : board.startFields.topRightFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else if (currentPlayer == 5){
                    for (BoardField field : board.startFields.topLeftFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }else {
                    for (BoardField field : board.startFields.topFields)
                        if (!field.getPlayerColor().equals(field.getCurrentPlayerColor()))
                            return false;
                }
                break;
            }
        }
        return true;
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
            } else if (field.getPlayerColor() != PlayerColor.NO_PLAYER) {
                this.setAvailableFields(board, board.fields.indexOf(field));
            }
        }
    }

    public void addAvailableField(int fieldIndex) {
        this.availableFields.add(fieldIndex);
    }

}
