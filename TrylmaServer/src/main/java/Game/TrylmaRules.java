package Game;

import java.util.ArrayList;

public class TrylmaRules implements Rules {
    ArrayList<Integer> availableFileds = new ArrayList<>();
    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public boolean moveIsCorrect() {
        return false;
    }

    public void setAvailableFields(int indexField) {


    }
}
