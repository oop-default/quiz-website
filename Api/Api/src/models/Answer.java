package models;

public class Answer {
    private String answer;
    private boolean correct;

    public Answer(String answer, boolean correct){
        this.answer=answer;
        this.correct=correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    @Override
    public boolean equals(Object obj) {
        Answer answ = (Answer)obj;
        return answ!=null && answ.isCorrect() == correct && answer.equals(answ.getAnswer());
    }
}
