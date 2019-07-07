package models;

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
    }
}
