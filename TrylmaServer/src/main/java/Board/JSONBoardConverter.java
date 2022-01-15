package Board;

/**
 * Converts a board to a JSON String
 * which can be sent to clients by the server.
 * Can be turned back to Board
 * by JSONBoardConverter.
 *
 */
public class JSONBoardConverter {
    private final StringBuilder jsonBuilder = new StringBuilder();

    /**
     * Converts a board to a JSON String
     *
     * @param board board we want to convert
     * @return JSON String that stores board data
     *
     */
    public String buildJSONBoard(Board board) {
        addContent("{fields:[");
        for (BoardField field : board.fields) {
            addContent("{\"row\":\"" + field.getRow() + "\",");
            addContent("\"column\":\"" + field.getColumn() + "\",");
            addContent("\"xFramePosition\":\"" + field.getXPosition() + "\",");
            addContent("\"yFramePosition\":\"" + field.getYPosition() + "\",");
            addContent("\"playerColorID\":\"" + field.getPlayerColor().playerColorID + "\",");
            addContent("\"statusColorID\":\"" + field.getStatusColor().statusColorID + "\"");
            if (field.equals(board.fields.get(board.fields.size() - 1))) {
                addContent("}");
                break;
            }
            addContent("},");
        }
        addContent("]}");

        return this.jsonBuilder.toString();
    }

    /**
     * Ads data to the JSON String
     * @param content data to add
     *
     */
    public void addContent(String content) {
        jsonBuilder.append(content);
    }
}
