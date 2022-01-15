package board;

import org.json.JSONException;

/**
 * Board builder interface
 *
 */
public interface BoardBuilder {

    /**
     * builds a board based on String json
     *
     * @param JSONBoard json that contains data about the board
     * @throws JSONException exception when parsing json
     *
     */
    void setBoardFields(String JSONBoard) throws JSONException;
}
