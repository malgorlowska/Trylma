package board;

import org.json.JSONException;

public interface BoardBuilder {
    void setBoardFields(String JSONBoard) throws JSONException;
}
