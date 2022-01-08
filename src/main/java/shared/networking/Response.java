package shared.networking;

public class Response {
    public String data, status;
    public Response(String data, String status) {
        this.data = data;
        this.status = status;
    }
}
