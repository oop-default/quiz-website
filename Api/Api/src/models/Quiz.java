package models;

<<<<<<< HEAD
import java.util.Date;

public class Quiz {
    private int id;
    private int authorId;
    private String title;
    private String description;
    private Date dateCreated;
    private String category;
    private int numPoints;


    public Quiz() {
    }

    public Quiz(int id, int authorId, String title, String description, Date dateCreated, String category, int numPoints) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.category = category;
        this.numPoints = numPoints;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
=======

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
>>>>>>> 1dcc1d182628f10b7347ea8bd58199f15e926728
    }
}
