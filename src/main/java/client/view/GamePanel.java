package client.view;

import shared.game.Coordinate;
import shared.game.Player;
import shared.game.Ship;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Player player;
    public GamePanel(Player player) {
        this.player = player;
    }
    public int size;
    public int left_starting_y, left_starting_x, right_starting_x, right_starting_y;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        size = 70;
        int y = 30;
        left_starting_x = 100;
        left_starting_y = 30;
        for (int horz = 0; horz < 10; horz++) {
            int x = 100;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        y = 30;
        right_starting_y = 30;
        right_starting_x = size * 10 + 40 + 100;
        for (int horz = 0; horz < 10; horz++) {
            int x = size * 10 + 40 + 100;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }

        for (Ship ship: player.shipArrangement.ships) {
            shipDrawing(g2d, ship, left_starting_x, left_starting_y);
        }

        for (Ship ship: player.enemy_dead_ships) {
            shipDrawing(g2d, ship, right_starting_x, right_starting_y);
        }
        for (Coordinate coordinate: player.enemy_successful_shots) {
            int x_cord = coordinate.x * size + left_starting_x;
            int y_cord = coordinate.y * size + left_starting_y;
            g2d.setColor(Color.GREEN);
            g2d.fillOval(x_cord, y_cord, size, size);
        }

        for (Coordinate coordinate: player.successful_shots) {
            int x_cord = coordinate.x * size + right_starting_x;
            int y_cord = coordinate.y * size + right_starting_y;
            g2d.setColor(Color.GREEN);
            g2d.fillOval(x_cord, y_cord, size, size);
        }

        for (Coordinate coordinate: player.enemy_failed_shots) {
            int x_cord = coordinate.x * size + left_starting_x;
            int y_cord = coordinate.y * size + left_starting_y;
            g2d.setColor(Color.RED);
            g2d.fillOval(x_cord, y_cord, size, size);
        }

        for (Coordinate coordinate: player.failed_shots) {
            int x_cord = coordinate.x * size + right_starting_x;
            int y_cord = coordinate.y * size + right_starting_y;
            g2d.setColor(Color.RED);
            g2d.fillOval(x_cord, y_cord, size, size);
        }

        g2d.dispose();
    }

    private void shipDrawing(Graphics2D g2d, Ship ship, int starting_x, int starting_y) {
        if (ship.coordinates.getFirst().y == ship.coordinates.getLast().y) {

            g2d.setColor(Color.CYAN);
            g2d.fillRect(starting_x + ship.coordinates.getFirst().x * size,
                    starting_y + ship.coordinates.getFirst().y * size
                    ,size * ship.coordinates.size(),size);
        }
        else {
            g2d.setColor(Color.CYAN);
            g2d.fillRect(starting_x + ship.coordinates.getFirst().x * size,
                    starting_y + ship.coordinates.getFirst().y * size
                    ,size,size * ship.coordinates.size());
        }
    }
}