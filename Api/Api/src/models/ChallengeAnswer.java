package models;

public class ChallengeAnswer {
    private int fromId;
    private int quizId;
    private String answer;

    public ChallengeAnswer(int fromId, int quizId, String answer){
        this.quizId = quizId;
        this.fromId = fromId;
        this.answer = answer;
    }

    public int getFromId() {
        return this.fromId;
    }

    public int getQuizId() {
        return this.quizId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ChallengeAnswer=[quizId="+quizId+",fromId="+fromId+",answer="+answer+"]";
    }
}
