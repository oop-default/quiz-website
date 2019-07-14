package models;

public class Challenge {
    private int receiverId;
    private int quizId;

    public Challenge(int receiverId, int quizId){
        this.quizId = quizId;
        this.receiverId = receiverId;
    }

    public int getReceiverId() {
        return this.receiverId;
    }

    public int getQuizId() {
        return this.quizId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
