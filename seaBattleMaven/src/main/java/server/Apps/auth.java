package server.apps;

import shared.gson.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;

import java.io.DataOutputStream;

public class auth {
    public void register(Request request, DataOutputStream dataOutputStream) {
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);

    }

    public void login(Request request, DataOutputStream dataOutputStream) {
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);

    }
}
