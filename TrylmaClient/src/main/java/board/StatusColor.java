package board;

public enum StatusColor {
    GREEN(2),
    RED(1);

    public final int statusColorID;

    StatusColor(int statusColorID) {
        this.statusColorID = statusColorID;
    }

    public static StatusColor fromInteger(int x) {
        if (x == 1) {
            return RED;
        }
        return GREEN;
    }
}
