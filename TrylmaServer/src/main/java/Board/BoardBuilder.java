package Board;

import org.json.JSONException;

public interface BoardBuilder {
    void initializeBoardFields();
    void setBoardFields(String JSONBoard) throws JSONException;
    void assignFields(int numberOfPlayers);
}
