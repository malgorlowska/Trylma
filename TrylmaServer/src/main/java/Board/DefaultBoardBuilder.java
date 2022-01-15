package Board;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Builds board based on one of int[][] arrays
 * that are stored in BoardShapes
 *
 */
public class DefaultBoardBuilder implements BoardBuilder{
    DefaultBoard board = new DefaultBoard();

    @Override
    public void initializeBoardFields(int[][] boardShape) {
       ArrayList<BoardField> fields = new ArrayList<>();
       DefaultStartFields startFields = new DefaultStartFields();

       int xPosition;
       int defXPosition = 50;
       int yPosition = 50;

       int pColorID = PlayerColor.NO_PLAYER.playerColorID;
       int sColorID = StatusColor.INACTIVE.statusColorID;

       for (int i = 0; i < boardShape.length; i++) {
           if(i % 2 == 0){xPosition = defXPosition;}
           else { xPosition = defXPosition + 25;}

           for (int j = 0; j < boardShape[0].length; j++) {
               if (boardShape[i][j] != 0) {
                   BoardField field = new BoardField(i, j, xPosition, yPosition, pColorID, sColorID);

                   startFields.addField(field, boardShape[i][j]);
                   fields.add(field);
               }
               xPosition += 40;
           }
           yPosition += 30;
       }
       this.board.setFields(fields);
       this.board.setStartFields(startFields);
    }

    @Override
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
        this.board.startFields.setPlayerColors(numberOfPlayers);
    }

    /**
     * @return returns a board created by the builder
     *
     */
    public DefaultBoard getDefaultBoard(){
        return this.board;
    }
}
