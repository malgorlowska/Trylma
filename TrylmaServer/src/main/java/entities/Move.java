package entities;

import javax.persistence.*;

@Entity
@Table(name="moves")
public class Move {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    //czy potrzebne column?
    @Column(name="startField")
    int startField;
    @Column(name="endField")
    int endField;
    @Column(name="playerId")
    int playerId;
    @Column(name="gameId")
    int gameId;

    @ManyToOne
    @JoinColumn(name="gameId", nullable=false)
    Game game;

    public Game getGame() { return game; }
}
