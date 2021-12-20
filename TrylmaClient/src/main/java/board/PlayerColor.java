package board;

public enum PlayerColor {
    BLUE(1),
    ORANGE(2),
    PINK(3),
    PURPLE(4),
    BLACK(5),
    WHITE(6),
    NO_PLAYER(7);

    public final int playerColorID;

    PlayerColor(int playerColorID){
        this.playerColorID = playerColorID;
    }
    public static PlayerColor fromInteger(int x) {
        switch(x) {
            case 1:
                return BLUE;
            case 2:
                return ORANGE;
            case 3:
                return PINK;
            case 4:
                return PURPLE;
            case 5:
                return BLACK;
            case 6:
                return WHITE;
            default:
                return NO_PLAYER;
        }
    }
}
