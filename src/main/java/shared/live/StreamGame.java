package shared.live;

import shared.game.Game;

public class StreamGame {
    public String player1, player2;
    public int player1_successful_shots, player2_successful_shots,
        player1_failed_shots, player2_failed_shots, player1_dead_ships,
            player2_dead_ships, gameID;
    public StreamGame(Game game) {
        player1 = game.player1.username;
        player2 = game.player2.username;
        player1_successful_shots = game.player1.successful_shots.size();
        player1_failed_shots = game.player1.failed_shots.size();
        player1_dead_ships = game.player1.dead_ships.size();
        player2_successful_shots = game.player2.successful_shots.size();
        player2_failed_shots = game.player2.failed_shots.size();
        player2_dead_ships = game.player2.dead_ships.size();
        gameID = game.id;
    }
}
