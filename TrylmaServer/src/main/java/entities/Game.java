package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name="numberOfPlayers")
    int numberOfPlayers;
    @Column(name="boardSize")
    int boardSize;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="customer")
    public Set<Move> moves;

    public Set<Move>getMoves() { return moves; }


    public Game(int players, int boardSize) {
    }
}
