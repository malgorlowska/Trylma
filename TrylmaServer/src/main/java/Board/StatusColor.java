package Board;

public enum StatusColor {
    ACTIVE(1),
    INACTIVE(2),
    POSSIBLE_MOVE(3);

    public final int statusColorID;

    StatusColor(int statusColorID) {
        this.statusColorID = statusColorID;
    }

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
