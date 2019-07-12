package models;

import java.util.Date;

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
}
