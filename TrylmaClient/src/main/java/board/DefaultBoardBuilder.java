package board;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DefaultBoardBuilder implements BoardBuilder{
    DefaultBoard board = new DefaultBoard();

    @Override
    public void setBoardFields() {
        int[][] defaultBoardShape =
       {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};

        ArrayList<BoardField> fields = new ArrayList<>();

        int xPosition;
        int defXPosition = 50;
        int yPosition = 10;

        for (int i = 0; i < defaultBoardShape.length; i++) {
            if(i % 2 == 0){xPosition = defXPosition;}
            else { xPosition = defXPosition + 25;}
            for (int j = 0; j < defaultBoardShape[0].length; j++){
                if(defaultBoardShape[i][j] == 1){
                    BoardField field = new BoardField(xPosition, yPosition);
                    field.setRow(i);
                    field.setColumn(j);
                    fields.add(field);
                }
                xPosition += 40;

            }
            yPosition += 30;
        }

        for ( BoardField field: fields) {
            field.setCurrentPlayerColor(PlayerColor.NO_PLAYER);
            field.setCurrentStatusColor(StatusColor.GREEN);
        }
        this.board.setFields(fields);

    }

    public void setBoardFields(String JSONBoard) throws JSONException {
        ArrayList<BoardField> fields = new ArrayList<>();
        JSONObject object = new JSONObject(JSONBoard);
        JSONArray fieldsArray = object.getJSONArray("fields");
        for (int i = 0; i < fieldsArray.length(); i++){
            JSONObject field = fieldsArray.getJSONObject(i);
            int row = field.getInt("row");
            int column = field.getInt("column");
            double xPosition = field.getDouble("xFramePosition");
            double yPosition = field.getDouble("yFramePosition");
            int playerColorID = field.getInt("playerColorID");
            int statusColorID = field.getInt("statusColorID");
            BoardField boardField = new BoardField(row, column, xPosition, yPosition, playerColorID, statusColorID);
            fields.add(boardField);
        }
        this.board.setFields(fields);
    }

    @Override
    public void assignFields(int numberOfPlayers) {

    }

    @Override
    public void addMouseListener() {

    }

    public DefaultBoard getDefaultBoard(){
        return this.board;
    }
}
