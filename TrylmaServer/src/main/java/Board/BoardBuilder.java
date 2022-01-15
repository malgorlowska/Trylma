package Board;

import org.json.JSONException;

/**
 * Board Builder interface
 * @see DefaultBoardBuilder
 *
 */
public interface BoardBuilder {

    /**
     * Initializes game board (its fields)
     * based on given int[][] array
     *
     * @param boardShape an array describing fields positions
     *
     */
    void initializeBoardFields(int[][] boardShape);

    /**
     * builds a board based on String json
     *
     * @param JSONBoard json that contains data about the board
     * @throws JSONException exception when parsing json
     *
     */
    void setBoardFields(String JSONBoard) throws JSONException;

    /**
     * Assigns board fields to players
     * according to they number
     *
     * @param numberOfPlayers number of players in the game
     */
    void assignFields(int numberOfPlayers);
}
