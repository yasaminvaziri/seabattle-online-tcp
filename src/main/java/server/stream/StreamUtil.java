package server.stream;

import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StreamUtil {
    public static void sendResponse(Response response, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(GsonUtil.getGson().toJson(response));
        dataOutputStream.flush();
    }
    public static Request getRequest(DataInputStream dataInputStream) throws IOException {
        return GsonUtil.getGson().fromJson(dataInputStream.readUTF(), Request.class);
    }
}
