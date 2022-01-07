package board;

public enum PlayerColor {
    BLUE(1),
    GREEN(2),
    YELLOW(3),
    ORANGE(4),
    PINK(5),
    PURPLE(6),
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
