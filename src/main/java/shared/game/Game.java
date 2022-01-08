package shared.game;

import java.util.HashMap;
import java.util.LinkedList;

public class Game {
    public static int GameId = 0;
    public static HashMap<Integer, Game> IdToGame = new HashMap<>();
    public static LinkedList<Game> games = new LinkedList<>();
    public Player player1, player2;
    public int id;
    public Game(String player1, String player2) {
        id = GameId++;
        games.add(this);
        IdToGame.put(id, this);
        this.player1 = new Player(player1, player2, id);
        this.player2 = new Player(player2, player1, id);
    }
}
