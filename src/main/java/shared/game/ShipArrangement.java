package shared.game;

import shared.random.randomNumberGenerator;

import java.util.LinkedList;

public class ShipArrangement {
    public LinkedList<Ship> ships;
    public int mapNumber;
    public ShipArrangement(LinkedList<Ship> ships, boolean s) {
        this.ships = new LinkedList<>(ships);
    }
    public ShipArrangement(LinkedList<Integer> choice) {
        ships = new LinkedList<>();
        int tmp = randomNumberGenerator.RandomInt(choice.size());
        mapNumber = choice.get(tmp);
        if (mapNumber == 0) {
            Ship ship4_1 = new Ship();
            ship4_1.coordinates.add(new Coordinate(1,0));
            ship4_1.coordinates.add(new Coordinate(2,0));
            ship4_1.coordinates.add(new Coordinate(3,0));
            ship4_1.coordinates.add(new Coordinate(4,0));
            ships.add(ship4_1);

            Ship ship3_1 = new Ship();
            ship3_1.coordinates.add(new Coordinate(7,2));
            ship3_1.coordinates.add(new Coordinate(7,3));
            ship3_1.coordinates.add(new Coordinate(7,4));
            ships.add(ship3_1);

            Ship ship3_2 = new Ship();
            ship3_2.coordinates.add(new Coordinate(1,8));
            ship3_2.coordinates.add(new Coordinate(2,8));
            ship3_2.coordinates.add(new Coordinate(3,8));
            ships.add(ship3_2);

            Ship ship2_1 = new Ship();
            ship2_1.coordinates.add(new Coordinate(3,2));
            ship2_1.coordinates.add(new Coordinate(4,2));
            ships.add(ship2_1);

            Ship ship2_2 = new Ship();
            ship2_2.coordinates.add(new Coordinate(2,4));
            ship2_2.coordinates.add(new Coordinate(2,5));
            ships.add(ship2_2);

            Ship ship2_3 = new Ship();
            ship2_3.coordinates.add(new Coordinate(6,7));
            ship2_3.coordinates.add(new Coordinate(6,8));
            ships.add(ship2_3);

            Ship ship1_1 = new Ship();
            ship1_1.coordinates.add(new Coordinate(8,0));
            ships.add(ship1_1);

            Ship ship1_2 = new Ship();
            ship1_2.coordinates.add(new Coordinate(9,4));
            ships.add(ship1_2);

            Ship ship1_3 = new Ship();
            ship1_3.coordinates.add(new Coordinate(4,5));
            ships.add(ship1_3);

            Ship ship1_4 = new Ship();
            ship1_4.coordinates.add(new Coordinate(8,8));
            ships.add(ship1_4);

        }
        else if (mapNumber == 1) {
            Ship ship4_1 = new Ship();
            ship4_1.coordinates.add(new Coordinate(3,4));
            ship4_1.coordinates.add(new Coordinate(4,4));
            ship4_1.coordinates.add(new Coordinate(5,4));
            ship4_1.coordinates.add(new Coordinate(6,4));
            ships.add(ship4_1);

            Ship ship3_1 = new Ship();
            ship3_1.coordinates.add(new Coordinate(2,0));
            ship3_1.coordinates.add(new Coordinate(2,1));
            ship3_1.coordinates.add(new Coordinate(2,2));
            ships.add(ship3_1);

            Ship ship3_2 = new Ship();
            ship3_2.coordinates.add(new Coordinate(6,2));
            ship3_2.coordinates.add(new Coordinate(7,2));
            ship3_2.coordinates.add(new Coordinate(8,2));
            ships.add(ship3_2);

            Ship ship2_1 = new Ship();
            ship2_1.coordinates.add(new Coordinate(4,1));
            ship2_1.coordinates.add(new Coordinate(4,2));
            ships.add(ship2_1);

            Ship ship2_2 = new Ship();
            ship2_2.coordinates.add(new Coordinate(1,6));
            ship2_2.coordinates.add(new Coordinate(2,6));
            ships.add(ship2_2);

            Ship ship2_3 = new Ship();
            ship2_3.coordinates.add(new Coordinate(5,7));
            ship2_3.coordinates.add(new Coordinate(5,8));
            ships.add(ship2_3);

            Ship ship1_1 = new Ship();
            ship1_1.coordinates.add(new Coordinate(8,4));
            ships.add(ship1_1);

            Ship ship1_2 = new Ship();
            ship1_2.coordinates.add(new Coordinate(8,6));
            ships.add(ship1_2);

            Ship ship1_3 = new Ship();
            ship1_3.coordinates.add(new Coordinate(7,8));
            ships.add(ship1_3);

            Ship ship1_4 = new Ship();
            ship1_4.coordinates.add(new Coordinate(3,9));
            ships.add(ship1_4);
        }
        else if (mapNumber == 2) {
            Ship ship4_1 = new Ship();
            ship4_1.coordinates.add(new Coordinate(8,1));
            ship4_1.coordinates.add(new Coordinate(8,2));
            ship4_1.coordinates.add(new Coordinate(8,3));
            ship4_1.coordinates.add(new Coordinate(8,4));
            ships.add(ship4_1);

            Ship ship3_1 = new Ship();
            ship3_1.coordinates.add(new Coordinate(2,2));
            ship3_1.coordinates.add(new Coordinate(3,2));
            ship3_1.coordinates.add(new Coordinate(4,2));
            ships.add(ship3_1);

            Ship ship3_2 = new Ship();
            ship3_2.coordinates.add(new Coordinate(1,7));
            ship3_2.coordinates.add(new Coordinate(1,8));
            ship3_2.coordinates.add(new Coordinate(1,9));
            ships.add(ship3_2);

            Ship ship2_1 = new Ship();
            ship2_1.coordinates.add(new Coordinate(5,0));
            ship2_1.coordinates.add(new Coordinate(6,0));
            ships.add(ship2_1);

            Ship ship2_2 = new Ship();
            ship2_2.coordinates.add(new Coordinate(3,4));
            ship2_2.coordinates.add(new Coordinate(4,4));
            ships.add(ship2_2);

            Ship ship2_3 = new Ship();
            ship2_3.coordinates.add(new Coordinate(5,6));
            ship2_3.coordinates.add(new Coordinate(5,7));
            ships.add(ship2_3);

            Ship ship1_1 = new Ship();
            ship1_1.coordinates.add(new Coordinate(6,4));
            ships.add(ship1_1);

            Ship ship1_2 = new Ship();
            ship1_2.coordinates.add(new Coordinate(1,5));
            ships.add(ship1_2);

            Ship ship1_3 = new Ship();
            ship1_3.coordinates.add(new Coordinate(3,8));
            ships.add(ship1_3);

            Ship ship1_4 = new Ship();
            ship1_4.coordinates.add(new Coordinate(8,8));
            ships.add(ship1_4);

        }
        else if (mapNumber == 3) {
            Ship ship4_1 = new Ship();
            ship4_1.coordinates.add(new Coordinate(5,1));
            ship4_1.coordinates.add(new Coordinate(5,2));
            ship4_1.coordinates.add(new Coordinate(5,3));
            ship4_1.coordinates.add(new Coordinate(5,4));
            ships.add(ship4_1);

            Ship ship3_1 = new Ship();
            ship3_1.coordinates.add(new Coordinate(0,4));
            ship3_1.coordinates.add(new Coordinate(1,4));
            ship3_1.coordinates.add(new Coordinate(2,4));
            ships.add(ship3_1);

            Ship ship3_2 = new Ship();
            ship3_2.coordinates.add(new Coordinate(4,7));
            ship3_2.coordinates.add(new Coordinate(4,8));
            ship3_2.coordinates.add(new Coordinate(4,9));
            ships.add(ship3_2);

            Ship ship2_1 = new Ship();
            ship2_1.coordinates.add(new Coordinate(2,1));
            ship2_1.coordinates.add(new Coordinate(2,2));
            ships.add(ship2_1);

            Ship ship2_2 = new Ship();
            ship2_2.coordinates.add(new Coordinate(7,6));
            ship2_2.coordinates.add(new Coordinate(8,6));
            ships.add(ship2_2);

            Ship ship2_3 = new Ship();
            ship2_3.coordinates.add(new Coordinate(0,8));
            ship2_3.coordinates.add(new Coordinate(1,8));
            ships.add(ship2_3);

            Ship ship1_1 = new Ship();
            ship1_1.coordinates.add(new Coordinate(8,1));
            ships.add(ship1_1);

            Ship ship1_2 = new Ship();
            ship1_2.coordinates.add(new Coordinate(8,9));
            ships.add(ship1_2);

            Ship ship1_3 = new Ship();
            ship1_3.coordinates.add(new Coordinate(7,4));
            ships.add(ship1_3);

            Ship ship1_4 = new Ship();
            ship1_4.coordinates.add(new Coordinate(1,6));
            ships.add(ship1_4);

        }
    }

}
