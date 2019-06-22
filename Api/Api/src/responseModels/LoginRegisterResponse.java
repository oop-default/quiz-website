package responseModels;

public class LoginRegisterResponse {
    private int status;
    private String jwsToken;
    private String message;
    public LoginRegisterResponse(int status,String jwsToken,String message){
        this.jwsToken=jwsToken;
        this.message=message;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJwsToken() {
        return jwsToken;
    }

    public void setJwsToken(String jwsToken) {
        this.jwsToken = jwsToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
