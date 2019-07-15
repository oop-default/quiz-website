package models;


import java.util.ArrayList;
import java.util.Objects;

public class Quiz {
    private int id;
    private String title;
    private String type;
    private String description;
    private String author;
    private int authorId;
    private ArrayList<Question> questions;
    private int num_points;
    private String dateCreated;

    public Quiz(int id, String title, String type, String description, String author, ArrayList<Question> questions, int num_points, String dateCreated) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.author = author;
        this.questions = questions;
        this.num_points = num_points;
        this.dateCreated = dateCreated;
    }

    public Quiz(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id &&
                num_points == quiz.num_points &&
                Objects.equals(title, quiz.title) &&
                Objects.equals(type, quiz.type) &&
                Objects.equals(description, quiz.description) &&
                Objects.equals(author, quiz.author) &&
                Objects.equals(questions, quiz.questions) &&
                Objects.equals(dateCreated, quiz.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, description, author, questions, num_points, dateCreated);
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
