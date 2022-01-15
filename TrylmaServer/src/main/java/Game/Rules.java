package Game;

import Board.*;

/**
 * Interface of  game rules
 *
 */
public interface Rules {

    /**
     * Rule set that determines
     * if given player won the game
     *
     * @param game the game that is being played
     * @param playerID id of player being checked
     * @return true if player won the game
     *
     */
    boolean isWinner(Game game, int playerID);

    /**
     * Rule set that determines if given move is correct.
     * Checks if selected field is in Available fields.
     * It is checked after second click by current player.
     *
     * @param board the game board in current state
     * @param endMoveField the field player is trying to move to
     * @return true if move is correct
     *
     */
    boolean moveIsCorrect(Board board, int endMoveField);

    /**
     * Rule set that maps legal fields
     * that you can move to from selected field.
     * It is checked after first click by current player.
     *
     * @param board
     * @param startField
     * @param isFirstCheck
     *
     */
    void setAvailableFields(Board board, BoardField startField, boolean isFirstCheck);
}
