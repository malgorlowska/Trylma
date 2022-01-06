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
    public boolean moveIsCorrect(Board board, int endMoveField) {
        return availableFields.contains(board.fields.indexOf(endMoveField));
    }

    @Override
    public void setAvailableFields(Board board, int startMoveField, boolean isFirstCheck) {
        BoardField startField = board.fields.get(startMoveField);
        System.out.println("szukam dostepnych pol dla [wiersz,columna] " + startField.getRow() +" " +startField.getColumn());
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
        System.out.println("Szukam sasiadow wiersz, kolumna " + currRow + " " + currColumn);
        for (BoardField field : board.fields) {
            int row = field.getRow();
            int column = field.getColumn();

            if ((row == currRow) && ((column == currColumn + 1) || (column == currColumn - 1))) {
                System.out.println("sasiadem jest wiersz, kolumna " + row + " " + column );
                neighbors.add(field);
            }

            if (currRow % 2 == 0) {
                if (((row == currRow - 1) || (row == currRow + 1)) && ((column == currColumn - 1) || (column == currColumn))) {
                    System.out.println("sasiadem jest wiersz, kolumna " + row + " " + column );
                    neighbors.add(field);
                }
            } else if (currRow % 2 == 1) {
                if (((row == currRow - 1) || (row == currRow + 1)) && ((column == currColumn + 1) || (column == currColumn))) {
                    System.out.println("sasiadem jest wiersz, kolumna " + row + " " + column );
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
