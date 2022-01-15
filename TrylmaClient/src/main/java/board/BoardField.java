package board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A single field of a board,
 * it stores information about
 * its color, status and position
 *
 */
public class BoardField extends Ellipse2D.Double {
    private Color playerColor_;
    private Color statusColor_;
    private PlayerColor playerColor;
    private StatusColor statusColor;
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

        this.setFrame(xPosition, yPosition, 25, 20);
        this.setRow(row);
        this.setColumn(column);
        PlayerColor playerColor = PlayerColor.fromInteger(playerColorID);
        StatusColor statusColor = StatusColor.fromInteger(statusColorID);
        this.setPlayerColor_(playerColor);
        this.setStatusColor_(statusColor);
    }

    /**
     * Checks if the field was clicked by the mouse
     * @param x - X axis mouse position
     * @param y - Y axis mouse position
     * @return true if mouse clicked on the field
     *
     */
    public boolean isHit(int x, int y) {
        return this.getBounds2D().contains(x, y);
    }

    /**
     * @return returns current field color
     *
     */
    public Color getPlayerColor_() {
        return playerColor_;
    }

    /**
     * Sets the field color
     * @param playerColor a new player color
     *
     */
    public void setPlayerColor_(PlayerColor playerColor) {
        this.playerColor = playerColor;
        switch (playerColor) {
            case BLUE:
                this.playerColor_ = new Color(65,105,225);
                break;
            case GREEN:
                this.playerColor_ = new Color(0,250,154);
                break;
            case YELLOW:
                this.playerColor_ = new Color(255,255,102);
                break;
            case ORANGE:
                this.playerColor_ = new Color(255,127,80);
                break;
            case PINK:
                this.playerColor_ = new Color(255,145,164);
                break;
            case PURPLE:
                this.playerColor_ = new Color(148,87,235);
                break;
            case NO_PLAYER:
                this.playerColor_ = new Color(196,195,208);
        }
    }

    /**
     * @return returns what is current field status
     *
     */
    public Color getStatusColor_() {
        return statusColor_;
    }

    /**
     * Sets the field status and border color
     * according to given status
     *
     * @param statusColor a new field status
     *
     */
    public void setStatusColor_(StatusColor statusColor) {
        this.statusColor = statusColor;
        switch (statusColor){
            case ACTIVE:
                this.statusColor_ = new Color(80, 235, 221);
                break;
            case INACTIVE:
                this.statusColor_ = new Color(36, 33, 36);
                break;
            case POSSIBLE_MOVE:
                this.statusColor_ = new Color(237, 74, 110);
                break;
        }
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
     * @return returns fields current status
     *
     */
    public StatusColor getStatusColor() {
        return statusColor;
    }
}