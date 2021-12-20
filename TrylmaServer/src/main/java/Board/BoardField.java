package Board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BoardField {
    private Color currentPlayerColor;
    private Color currentStatusColor;
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
        PlayerColor playerColor = PlayerColor.fromInteger(playerColorID);
        StatusColor statusColor = StatusColor.fromInteger(statusColorID);
        this.setCurrentPlayerColor(playerColor);
        this.setCurrentStatusColor(statusColor);
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public void setCurrentPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
        switch (playerColor) {
            case BLUE:
                this.currentPlayerColor  = Color.CYAN;
            case ORANGE:
                this.currentPlayerColor = Color.ORANGE;
            case PINK:
                this.currentPlayerColor = Color.PINK;
            case PURPLE:
                this.currentPlayerColor = Color.MAGENTA;
            case BLACK:
                this.currentPlayerColor = Color.BLACK;
            case WHITE:
                this.currentPlayerColor = Color.WHITE;
            case NO_PLAYER:
                this.currentPlayerColor = Color.GRAY;
        }
    }


    public Color getCurrentStatusColor() {
        return currentStatusColor;
    }

    public void setCurrentStatusColor(StatusColor statusColor) {
        this.statusColor = statusColor;
        switch (statusColor){
            case RED -> this.currentStatusColor = Color.RED;
            case GREEN -> this.currentStatusColor = Color.GREEN;
        }
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

    public StatusColor getStatusColor() {
        return statusColor;
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
