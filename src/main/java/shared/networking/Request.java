package shared.networking;

public class Request {
    public String type, data, token;
    public int gameID;
    public Request(String type, String data) {
        this.type = type;
        this.data = data;
    }
    public Request(String type, String data, String token) {
        this.type = type;
        this.data = data;
        this.token = token;
    }
    public Request(String type, String data, String token, int gameID) {
        this.type = type;
        this.data = data;
        this.token = token;
        this.gameID = gameID;
    }
}
