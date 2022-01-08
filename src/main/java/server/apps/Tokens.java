package server.apps;

import server.models.User;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

public class Tokens {
    public static HashMap<String , User > tokenMap = new HashMap<>();
    public static String generateToken(User user) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[30];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        tokenMap.put(token, user);
        return token;
    }

}
