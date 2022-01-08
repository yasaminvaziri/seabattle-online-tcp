package server.models;

import server.dataBase.Load;

public class User {
    public String username, password;
    public int wins, loses;
    private int id;

    public int getId() {
        return id;
    }

    public User(String username, String password) {
        Load.userList.add(this);
        this.id = (int) (Load.lastIdUser() + 1);
        this.username = username;
        this.password = password;
        wins = loses = 0;
    }
}
