package responseModels;

public class NotPersonalScoreResponse {
    private int pos;
    private String user;
    private int score;
    public NotPersonalScoreResponse(int pos, String user, int score){
        this.pos = pos;
        this.user = user;
        this.score = score;
    }
}
