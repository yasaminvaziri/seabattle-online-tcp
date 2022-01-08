package shared.game;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Player {
    public LinkedList<Integer> mapChoices;
    public ShipArrangement shipArrangement;
    public LinkedList<Coordinate> successful_shots, failed_shots, enemy_successful_shots, enemy_failed_shots;
    public LinkedList<Ship> dead_ships, enemy_dead_ships;
    public String username, enemy_username;
    public String state;
    public LocalDateTime endTurn;
    public int ID;
    public Player(Player player) {
        ID = player.ID;
        endTurn = player.endTurn;
        state = player.state;
        username = player.username;
        enemy_username = player.enemy_username;
        shipArrangement = new ShipArrangement(player.dead_ships, true);
        successful_shots = new LinkedList<>(player.successful_shots);
        failed_shots = new LinkedList<>(player.failed_shots);
        enemy_failed_shots = new LinkedList<>(player.enemy_failed_shots);
        enemy_successful_shots = new LinkedList<>(player.enemy_successful_shots);
        enemy_dead_ships = new LinkedList<>(player.enemy_dead_ships);
    }
    public Player(String username, String enemy_username, int ID) {
        this.ID = ID;
        mapChoices = new LinkedList<>();
        mapChoices.add(0);
        mapChoices.add(1);
        mapChoices.add(2);
        mapChoices.add(3);
        shipArrangement = new ShipArrangement(mapChoices);
        mapChoices.remove(Integer.valueOf(shipArrangement.mapNumber));
        successful_shots = new LinkedList<>();
        failed_shots = new LinkedList<>();
        enemy_failed_shots = new LinkedList<>();
        enemy_successful_shots = new LinkedList<>();
        dead_ships = new LinkedList<>();
        enemy_dead_ships = new LinkedList<>();
        this.username = username;
        this.enemy_username = enemy_username;
        endTurn = LocalDateTime.now().plusSeconds(30);
        state = "arranging";
    }
}
