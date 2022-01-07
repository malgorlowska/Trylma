package Board;


public class BoardField {
    private PlayerColor playerColor;
    private StatusColor statusColor;
    private double xPosition;
    private double yPosition;
    private int row;
    private int column;


    public BoardField(int row, int column, double xPosition, double yPosition,
                      int playerColorID, int statusColorID) {

        this.setRow(row);
        this.setColumn(column);
        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
        this.setPlayerColor(PlayerColor.fromInteger(playerColorID));
        this.setStatusColor(StatusColor.fromInteger(statusColorID));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public StatusColor getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(StatusColor statusColor) {
        this.statusColor = statusColor;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
