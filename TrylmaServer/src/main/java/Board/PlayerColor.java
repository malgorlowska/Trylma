package Board;

/**
 * Enum with possible player colors
 *
 */
public enum PlayerColor {
    BLUE(1),
    GREEN(2),
    YELLOW(3),
    ORANGE(4),
    PINK(5),
    PURPLE(6),
    NO_PLAYER(7);

    public final int playerColorID;

    /**
     * Each color is associated with a number (id)
     * @param playerColorID color id
     *
     */
    PlayerColor(int playerColorID){
        this.playerColorID = playerColorID;
    }

    /**
     * Converts a number to a color
     * @param x number given to convert
     * @return color associated with a given number
     *
     */
    public static PlayerColor fromInteger(int x) {
        switch(x) {
            case 1:
                return BLUE;
            case 2:
                return GREEN;
            case 3:
                return YELLOW;
            case 4:
                return ORANGE;
            case 5:
                return PINK;
            case 6:
                return PURPLE;
            default:
                return NO_PLAYER;
        }
    }
}
