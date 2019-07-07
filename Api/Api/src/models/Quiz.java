package models;


import java.util.ArrayList;

public class Quiz {
    private int id;
    private String title;
    private String type;
    private String description;
    private String author;
    private ArrayList<Question> questions;
    private int num_points;
    private String dateCreated;
    public Quiz(int id,String title, String type, String description, String author, int num_points,ArrayList<Question> questions,String dateCreated){
        this.id=id;
        this.title=title;
        this.type=type;
        this.description=description;
        this.author=author;
        this.num_points=num_points;
        this.questions=questions;
        this.dateCreated=dateCreated;
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum_points() {
        return num_points;
    }

    public void setNum_points(int num_points) {
        this.num_points = num_points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
