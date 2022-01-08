package shared.scores;

import server.models.User;
import shared.UserInfo;

import java.util.Collections;
import java.util.LinkedList;

public class Scores {
    public LinkedList<UserInfo> scores;

    public Scores(LinkedList<User> users) {
        scores = new LinkedList<>();
        for (User user:users) {
            scores.add(new UserInfo(user));
        }
        Collections.sort(scores, Collections.reverseOrder());
    }
}
