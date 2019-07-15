package models;

import java.util.Date;
import java.util.Objects;

public class SubmittedQuiz {
    private int personID;
    private int quizID;
    private int points;
    private long timeSpent;
    private long dateSubmittedMillis;
    public SubmittedQuiz(int personID,int quizID,int points,long timeSpent,long dateSubmittedMillis){
        this.personID = personID;
        this.quizID = quizID;
        this.points = points;
        this.timeSpent = timeSpent;
        this.dateSubmittedMillis = dateSubmittedMillis;
    }

    public int getQuizID(){

        return quizID;
    }

    public int getPersonID(){

        return personID;
    }
    public int getPoints(){

        return points;
    }
    public long getTimeSpent(){

        return timeSpent;
    }
    public long getDateSubmittedMillis(){

        return dateSubmittedMillis/1000;
    }
    public void setPersonID(int personID){
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmittedQuiz that = (SubmittedQuiz) o;
        return personID == that.personID &&
                quizID == that.quizID &&
                points == that.points &&
                timeSpent == that.timeSpent &&
                dateSubmittedMillis == that.dateSubmittedMillis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, quizID, points, timeSpent, dateSubmittedMillis);
    }
}
