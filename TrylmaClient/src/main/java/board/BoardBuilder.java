package board;

import org.json.JSONException;

public interface BoardBuilder {
    void setBoardFields(String JSONBoard) throws JSONException;
    void assignFields(int numberOfPlayers);
    void addMouseListener();
}
