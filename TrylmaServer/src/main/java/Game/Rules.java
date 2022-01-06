package Game;

import Board.*;

public interface Rules {

    public boolean isWinner();

    public boolean moveIsCorrect(Board board, BoardField endMoveField);
}
