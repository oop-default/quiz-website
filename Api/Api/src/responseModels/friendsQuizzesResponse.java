package responseModels;

public class friendsQuizzesResponse {
    private int friendId;
    private int quizId;
    private String friendName;
    private String quizName;
    private int points;

    public friendsQuizzesResponse(int friendId, String friendName, int quizId, String quizName, int points) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.quizId = quizId;
        this.quizName = quizName;
        this.points = points;
    }

}
