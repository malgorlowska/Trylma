package Board;

/**
 * A single field of a board,
 * it stores information about
 * its color, status and position
 *
 */
public class BoardField {
    private PlayerColor playerColor;
    private StatusColor statusColor;
    private double xPosition;
    private double yPosition;
    private int row;
    private int column;

    /**
     * BoardField constructor,
     * the board is made based on an int[][] array
     * the field can be identified based on its position in that array
     *
     * @param row - the row of an array associated to this field
     * @param column - the column of an array associated to this field
     * @param xPosition - X axis position on the screen
     * @param yPosition - Y axis position on the screen
     * @param playerColorID - field current color
     * @param statusColorID - field current status
     *
     */
    public BoardField(int row, int column, double xPosition, double yPosition,
                      int playerColorID, int statusColorID) {

        this.setRow(row);
        this.setColumn(column);
        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
        this.setPlayerColor(PlayerColor.fromInteger(playerColorID));
        this.setStatusColor(StatusColor.fromInteger(statusColorID));
    }

    /**
     * @return returns field row
     *
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets field row
     * @param row row the field is in
     *
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return returns field column
     *
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets field column
     * @param column column that the field is in
     *
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return returns fields current color
     *
     */
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Sets the field color
     * @param playerColor a new player color
     *
     */
    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * @return returns what is current field status
     *
     */
    public StatusColor getStatusColor() {
        return statusColor;
    }

    /**
     * Sets the field status
     * @param statusColor a new field status
     *
     */
    public void setStatusColor(StatusColor statusColor) {
        this.statusColor = statusColor;
    }

    /**
     * @return returns field X axis position
     *
     */
    public double getXPosition() {
        return xPosition;
    }

    /**
     * Sets field X axis position
     * @param xPosition field X axis position
     *
     */
    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * @return returns field Y axis position
     *
     */
    public double getYPosition() {
        return yPosition;
    }

    /**
     * Sets field Y axis position
     * @param yPosition field Y axis position
     *
     */
    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
