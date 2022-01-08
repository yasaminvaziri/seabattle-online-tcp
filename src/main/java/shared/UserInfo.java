package shared;

import server.models.User;

import java.time.LocalDateTime;

public class UserInfo implements Comparable<UserInfo> {
    public String username;
    public boolean online;
    public Integer points, wins, loses;
    public UserInfo(User user) {
        username = user.username;
        online = LocalDateTime.now().isBefore(user.lastOnline.plusSeconds(30));
        wins = user.wins;
        loses = user.loses;
        points = wins - loses;
    }
    @Override
    public int compareTo(UserInfo userInfo) {
        return this.points.compareTo(userInfo.points);
    }
}
