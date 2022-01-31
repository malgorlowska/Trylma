package database;

import entities.Game;
import entities.Move;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MovingController {
    ApplicationContext context;
    Game game;
    List<Move> moves;

    public MovingController(){
        context = new ClassPathXmlApplicationContext("resources/configuration.xml");
    }

    public MovingController(int players, int boardSize){
        context = new ClassPathXmlApplicationContext("resources/configuration.xml");
        game = new Game(players, boardSize);
        moves = new ArrayList<>();
    }
}
