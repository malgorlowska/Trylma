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
                      int playerColorID, int statusColorID) {

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
            case BLUE:
                this.currentPlayerColor  = new Color(65,105,225);
                break;
            case GREEN:
                this.currentPlayerColor = new Color(0,250,154);
                break;
            case YELLOW:
                this.currentPlayerColor = new Color(255,255,102);
                break;
            case ORANGE:
                this.currentPlayerColor = new Color(255,127,80);
                break;
            case PINK:
                this.currentPlayerColor = new Color(255,145,164);
                break;
            case PURPLE:
                this.currentPlayerColor = new Color(148,87,235);
                break;
            case NO_PLAYER:
                this.currentPlayerColor = new Color(196,195,208);
        }
    }


    public Color getCurrentStatusColor() {
        return currentStatusColor;
    }

    public void setCurrentStatusColor(StatusColor statusColor) {
        this.statusColor = statusColor;
        switch (statusColor){
            case ACTIVE:
                this.currentStatusColor = new Color(80, 235, 221);
                break;
            case INACTIVE:
                this.currentStatusColor = new Color(36, 33, 36);
                break;
            case POSSIBLE_MOVE:
                this.currentStatusColor = new Color(237, 74, 110);
                break;
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