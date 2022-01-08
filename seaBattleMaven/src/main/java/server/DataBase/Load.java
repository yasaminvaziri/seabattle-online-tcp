package server.dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Load {
    public static LinkedList<User> userList = new LinkedList<>();

    public static User loadUser(int id){
        for (User user : userList){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
    static public void loadUser(){
        File file = new File("resources/saveUser");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
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

}
