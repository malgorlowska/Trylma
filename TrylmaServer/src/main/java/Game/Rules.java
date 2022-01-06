package Game;

import Board.*;

public interface Rules {

    public boolean isWinner(Board board, int currentPlayer);

    public boolean moveIsCorrect(Board board, BoardField endMoveField);
}
