package client.stream;

import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StreamUtil {
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;
    public static void sendRequest(Request request) {
        System.out.println(request.type);
        try {
            dataOutputStream.writeUTF(GsonUtil.getGson().toJson(request));
            dataOutputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Response getResponse() {
        try {
            System.out.println("getting response");
            return GsonUtil.getGson().fromJson(dataInputStream.readUTF(), Response.class);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;

    }




}
