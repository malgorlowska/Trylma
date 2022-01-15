package board;

/**
 * Enum with possible status colors
 *
 */
public enum StatusColor {
    ACTIVE(1),
    INACTIVE(2),
    POSSIBLE_MOVE(3);

    public final int statusColorID;

    /**
     * Each status is associated with a number (id)
     * @param statusColorID status id
     *
     */
    StatusColor(int statusColorID) {
        this.statusColorID = statusColorID;
    }

    /**
     * Converts a number to a status
     * @param x number given to convert
     * @return status associated with a given number
     *
     */
    public static StatusColor fromInteger(int x) {
        if (x == 1) {
            return ACTIVE;
        }
        else if (x == 2) {
            return INACTIVE;
        }
        else {
            return POSSIBLE_MOVE;
        }
    }
}
