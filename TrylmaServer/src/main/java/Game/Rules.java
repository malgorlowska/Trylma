package Game;

import Board.*;

public interface Rules {

    public boolean isWinner(Board board, int playerID);

    boolean moveIsCorrect(Board board, int endMoveField);

    public void setAvailableFields(Board board, int startMoveField, boolean isFirstCheck);
}
