package board;

public enum StatusColor {
    RED(1),
    GREEN(2),
    POSSIBLE_MOVE(3);

    public final int statusColorID;

    StatusColor(int statusColorID) {
        this.statusColorID = statusColorID;
    }

    public static StatusColor fromInteger(int x) {
        if (x == 1) {
            return RED;
        }
        else if (x == 2) {
            return GREEN;
        }
        else {
            return POSSIBLE_MOVE;
        }
    }
}
