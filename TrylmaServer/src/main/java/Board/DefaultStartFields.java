package Board;

import java.util.ArrayList;

/**
 * Stores references to start fields.
 * Start fields are important part of the Board,
 * they determine where players start their games
 * and allow checking if one of the players won.
 *
 */
public class DefaultStartFields {
    public ArrayList<BoardField> topFields = new ArrayList<>();
    public ArrayList<BoardField> topLeftFields = new ArrayList<>();
    public ArrayList<BoardField> topRightFields = new ArrayList<>();
    public ArrayList<BoardField> bottomFields = new ArrayList<>();
    public ArrayList<BoardField> bottomLeftFields = new ArrayList<>();
    public ArrayList<BoardField> bottomRightFields = new ArrayList<>();

    /**
     * Adds field to the right starting fields
     *
     * @param field the field we are adding
     * @param fieldCharacteristic field characteristic number in  int[][] array
     *
     */
    public void addField(BoardField field, int fieldCharacteristic) {
        switch (fieldCharacteristic) {
            case 1 : {
                topFields.add(field);
                break;
            }
            case 2 : {
                topRightFields.add(field);
                break;
            }
            case 3 : {
                bottomRightFields.add(field);
                break;
            }
            case 4 : {
                bottomFields.add(field);
                break;
            }
            case 5 : {
                bottomLeftFields.add(field);
                break;
            }
            case 6 : {
                topLeftFields.add(field);
                break;
            }
        }
    }

    /**
     * Assigns start fields to players
     * at the start of the game
     * @param numOfPlayers number of players in the game
     *
     */
    public void setPlayerColors(int numOfPlayers) {
        switch (numOfPlayers) {
            case 2: {
                for (BoardField field : this.topFields) {
                    field.setPlayerColor(PlayerColor.BLUE);
                }
                for (BoardField field : this.bottomFields) {
                    field.setPlayerColor(PlayerColor.GREEN);
                }
                break;
            }
            case 3: {
                for (BoardField field : this.topLeftFields) {
                    field.setPlayerColor(PlayerColor.BLUE);
                }
                for (BoardField field : this.topRightFields) {
                    field.setPlayerColor(PlayerColor.GREEN);
                }
                for (BoardField field : this.bottomFields) {
                    field.setPlayerColor(PlayerColor.YELLOW);
                }
                break;
            }
            case 4: {
                for (BoardField field : this.topLeftFields) {
                    field.setPlayerColor(PlayerColor.BLUE);
                }
                for (BoardField field : this.topRightFields) {
                    field.setPlayerColor(PlayerColor.GREEN);
                }
                for (BoardField field : this.bottomLeftFields) {
                    field.setPlayerColor(PlayerColor.YELLOW);
                }
                for (BoardField field : this.bottomRightFields) {
                    field.setPlayerColor(PlayerColor.ORANGE);
                }
                break;
            }
            case 6: {
                for (BoardField field : this.topFields) {
                    field.setPlayerColor(PlayerColor.BLUE);
                }
                for (BoardField field : this.topLeftFields) {
                    field.setPlayerColor(PlayerColor.GREEN);
                }
                for (BoardField field : this.topRightFields) {
                    field.setPlayerColor(PlayerColor.YELLOW);
                }
                for (BoardField field : this.bottomLeftFields) {
                    field.setPlayerColor(PlayerColor.ORANGE);
                }
                for (BoardField field : this.bottomRightFields) {
                    field.setPlayerColor(PlayerColor.PINK);
                }
                for (BoardField field : this.bottomFields) {
                    field.setPlayerColor(PlayerColor.PURPLE);
                }
                break;
            }
        }
    }
}
