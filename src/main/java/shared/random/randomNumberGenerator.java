package shared.random;

import java.util.Random;

public class randomNumberGenerator {
    public static int RandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
