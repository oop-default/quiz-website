package models;

public class Score {
    private int position,score;
    private String user;
    public Score(int position,String user,int score) {
        this.position = position;
        this.score = score;
        this.user = user;
    }

    public int getPosition(){return position;}
    public int getScore(){return score;}
    public String getUser(){return user;}

}
