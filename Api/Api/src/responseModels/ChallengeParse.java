package responseModels;

public class ChallengeParse {
    private int fromId;
    private String fromName;
    private int quizId;

    public ChallengeParse(int fromId, String fromName, int quizId) {
        this.fromId = fromId;
        this.fromName = fromName;
        this.quizId = quizId;
    }


    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
