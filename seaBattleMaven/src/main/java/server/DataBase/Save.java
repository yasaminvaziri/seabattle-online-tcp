package server.dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.models.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Save {
    public static void saveUser(User user) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        String s = gson.toJson(User.class);
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
            gson.toJson(User.class, printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
        }

    }
}
