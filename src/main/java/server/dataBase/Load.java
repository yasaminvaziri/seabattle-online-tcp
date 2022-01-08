package server.dataBase;

import com.google.gson.Gson;
import server.models.User;
import shared.gson.GsonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Load {
    public static LinkedList<User> userList = new LinkedList<>();

    public static User loadUser(String username){
        for (User user : userList){
            if (user.username.equals(username)){
                return user;
            }
        }
        return null;
    }
    static public void loadUser(){
        File file = new File("resources/saveUser");

        Gson gson = GsonUtil.getGson();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                User user = gson.fromJson(fileReader,User.class);
                fileReader.close();
                userList.add(user);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static long lastIdUser(){
        int max = 0;
        for (User user : userList){
            max = Math.max(max, user.getId());
        }
        return max;
    }

    public static boolean UserExists(String username) {
        for (User user: userList) {
            if (user.username.equals(username))
                return true;
        }
        return false;
    }

}
