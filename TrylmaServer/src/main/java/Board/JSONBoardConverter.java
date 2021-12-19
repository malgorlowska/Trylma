package Board;

public class JSONBoardConverter {
    private final StringBuilder jsonBuilder = new StringBuilder();

    public String buildJSONBoard(Board board) {
        jsonBuilder.append("{fields:[");
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

    public void addContent(String content) {
        jsonBuilder.append(content);
    }
}
