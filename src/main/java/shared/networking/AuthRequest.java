package shared.networking;

public class AuthRequest {
    public String username, password, password2;
    public AuthRequest(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }
}
