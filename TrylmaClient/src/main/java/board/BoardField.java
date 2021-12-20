package board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BoardField extends Ellipse2D.Double {
    private Color currentPlayerColor;
    private Color currentStatusColor;
    private PlayerColor playerColor;
    private StatusColor statusColor;
    private int row;
    private int column;

    public BoardField(int row, int column, double xPosition, double yPosition,
                      int playerColorID, int statusColorID){

        this.setFrame(xPosition, yPosition, 25, 20);
        this.setRow(row);
        this.setColumn(column);
        PlayerColor playerColor = PlayerColor.fromInteger(playerColorID);
        StatusColor statusColor = StatusColor.fromInteger(statusColorID);
        this.setCurrentPlayerColor(playerColor);
        this.setCurrentStatusColor(statusColor);
    }

    public boolean isHit(int x, int y) {
        return this.getBounds2D().contains(x, y);
    }


    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public void setCurrentPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
        switch (playerColor) {
            case BLUE -> this.currentPlayerColor  = Color.CYAN;
            case ORANGE -> this.currentPlayerColor = Color.ORANGE;
            case PINK -> this.currentPlayerColor = Color.PINK;
            case PURPLE -> this.currentPlayerColor = Color.MAGENTA;
            case BLACK -> this.currentPlayerColor = Color.BLACK;
            case WHITE -> this.currentPlayerColor = Color.WHITE;
            case NO_PLAYER -> this.currentPlayerColor = Color.GRAY;
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
}