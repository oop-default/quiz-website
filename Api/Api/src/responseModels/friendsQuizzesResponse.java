package responseModels;

public class friendsQuizzesResponse {
    private int friendId;
    private int quizId;
    private String friendName;
    private String quizName;
    private int points;

    public friendsQuizzesResponse(int quizId, int friendId, String friendName, String quizName, int points) {
        this.quizId = quizId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.quizName = quizName;
        this.points = points;
    }

}
