package Game;

import Board.*;

public interface Rules {

    boolean isWinner(Game game, int playerID);

    boolean moveIsCorrect(Board board, int endMoveField);

    void setAvailableFields(Board board, BoardField startField, boolean isFirstCheck);
}
