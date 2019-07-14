package models;

import java.awt.*;
import java.util.ArrayList;

public class Question {
    private String type;
    private String question;
    private double num_point;
    private Image image;
    private ArrayList<Answer> answers;
    

    public Question(String type, String question,double num_point,Image image,ArrayList<Answer> answers){
        this.type=type;
        this.question=question;
        this.num_point=num_point;
        this.image=image;
        this.answers=answers;
    }
    public void setAnswers(ArrayList<Answer> answers){
        this.answers=answers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getNum_poin() {
        return num_point;
    }

    public void setNum_poin(double num_point) {
        this.num_point = num_point;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
