package Game;

import Board.*;
import java.util.ArrayList;

/**
 * Game rules
 * After current player first click
 * it maps all available fields
 * player can move to from the field he selected.
 * Based on that, after second click
 * it checks if the move is correct.
 * It also can check if player won.
 *
 */
public class TrylmaRules implements Rules {
    private ArrayList<Integer> availableFields = new ArrayList<>();
    private final int playersCount;

    /**
     * Basic constructor
     * @param playersCount amount of players playing the game
     *
     */
    public TrylmaRules(int playersCount) {
        this.playersCount = playersCount;
    }

    /**
     * Checks if given field is in target area
     * @param board current board
     * @param startMoveField the field player is on
     * @param currentPlayer id of the current player
     * @return true if field is in target area
     *
     */
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
                } else {
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
                } else if (currentPlayer == 3) {
                    for (BoardField field : board.startFields.topRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else {
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
                } else if (currentPlayer == 3) {
                    for (BoardField field : board.startFields.bottomLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else if (currentPlayer == 4) {
                    for (BoardField field : board.startFields.topRightFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else if (currentPlayer == 5) {
                    for (BoardField field : board.startFields.topLeftFields)
                        if (field.getRow() == currRow && field.getColumn() == currColumn)
                            return true;
                } else {
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
    public boolean isWinner(Game game, int currentPlayer) {
        PlayerColor currentPlayerColor = PlayerColor.fromInteger(currentPlayer);
        switch (playersCount) {
            case 2: {
                if (currentPlayer == 1) {
                    for (BoardField field : game.getBoard().startFields.bottomFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else {
                    for (BoardField field : game.getBoard().startFields.topFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                }
                break;
            }

            case 3: {
                if (currentPlayer == 1) {
                    for (BoardField field : game.getBoard().startFields.bottomRightFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : game.getBoard().startFields.bottomLeftFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else {
                    for (BoardField field : game.getBoard().startFields.topFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                }
                break;
            }

            case 4: {
                if (currentPlayer == 1) {
                    for (BoardField field : game.getBoard().startFields.bottomRightFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : game.getBoard().startFields.bottomLeftFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 3) {
                    for (BoardField field : game.getBoard().startFields.topRightFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else {
                    for (BoardField field : game.getBoard().startFields.topLeftFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                }
                break;
            }

            case 6: {
                if (currentPlayer == 1) {
                    for (BoardField field : game.getBoard().startFields.bottomFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 2) {
                    for (BoardField field : game.getBoard().startFields.bottomRightFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 3) {
                    for (BoardField field : game.getBoard().startFields.bottomLeftFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 4) {
                    for (BoardField field : game.getBoard().startFields.topRightFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else if (currentPlayer == 5) {
                    for (BoardField field : game.getBoard().startFields.topLeftFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                } else {
                    for (BoardField field : game.getBoard().startFields.topFields)
                        if (!(field.getPlayerColor() == currentPlayerColor))
                            return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public boolean moveIsCorrect(Board board, int endMoveField) {
        return availableFields.contains(endMoveField);
    }

    @Override
    public synchronized void setAvailableFields(Board board, BoardField currField, boolean isFirstCheck) {
        ArrayList<BoardField> neighbors = this.findNeighbors(board, currField);
        boolean wasChecked = false;

        if (this.availableFields.contains(board.fields.indexOf(currField))) {
            wasChecked = true;
        }

        if (!isFirstCheck) {
            this.availableFields.add(board.fields.indexOf(currField));
        }

        for (BoardField field : neighbors) {
            if (field.getPlayerColor() == PlayerColor.NO_PLAYER && isFirstCheck){
                this.availableFields.add(board.fields.indexOf(field));
            }
            else if (field.getPlayerColor() != PlayerColor.NO_PLAYER) {
                if (!wasChecked) {
                    this.tryToJump(board, currField, field);
                }
            }
        }
    }

    /**
     * Rules responsible for jumping
     * Adds fields you can jump on to available fields
     *
     * @param board current board
     * @param currField field we are on
     * @param fieldToPass field we want to jump over
     *
     */
    public synchronized void tryToJump(Board board, BoardField currField, BoardField fieldToPass) {
        ArrayList<BoardField> passFieldNeighbors = this.findNeighbors(board, fieldToPass);

        if (currField.getRow() == fieldToPass.getRow()) {
            for (BoardField field_ : passFieldNeighbors) {
                if (field_.getPlayerColor() == PlayerColor.NO_PLAYER) {
                    if (!field_.equals(currField) && field_.getRow() == currField.getRow()) {

                        this.setAvailableFields(board, field_, false);
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

                        this.setAvailableFields(board, field_, false);
                    }
                }
            }
        }
    }

    /**
     * Finds all neighbors of given field
     * and returns them in form of an array list
     *
     * @param board current board
     * @param currField field we are on
     * @return all fields that are neighbors to currField
     *
     */
    public synchronized ArrayList<BoardField> findNeighbors(Board board, BoardField currField) {
        ArrayList<BoardField> neighbors = new ArrayList<>();
        int currRow = currField.getRow();
        int currColumn = currField.getColumn();

        for (BoardField field : board.fields) {
            int row = field.getRow();
            int column = field.getColumn();

            if ((row == currRow) && ((column == currColumn + 1) || (column == currColumn - 1))) {
                System.out.println("sasiadem jest wiersz, kolumna " + row + " " + column );
                neighbors.add(field);
            }

            if (currRow % 2 == 0) {
                if (((row == currRow - 1) || (row == currRow + 1))
                        && ((column == currColumn - 1) || (column == currColumn))) {

                    neighbors.add(field);
                }
            }
            else if (currRow % 2 == 1) {
                if (((row == currRow - 1) || (row == currRow + 1))
                        && ((column == currColumn + 1) || (column == currColumn))) {

                    neighbors.add(field);
                }
            }
        }
        return neighbors;
    }

    /**
     * @return returns all available fields
     *
     */
    public ArrayList<Integer> getAvailableFields() {
        return this.availableFields;
    }

    /**
     * Resets available fields after each move
     *
     */
    public void resetAvailableFields() {
        this.availableFields.clear();
    }

}
