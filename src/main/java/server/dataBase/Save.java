package server.dataBase;

import com.google.gson.Gson;
import server.models.User;
import shared.gson.GsonUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Save {
    public static void saveUser(User user) {
        Gson gson = GsonUtil.getGson();
        String s = gson.toJson(user, User.class);
        String path = "resources/saveUser";
        path +="/" + (user.getId());
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(s);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
        }

    }
}
