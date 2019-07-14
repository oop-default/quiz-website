package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Question {
    private String type;
    private String question;
    private double num_point;
    private String image;
    private ArrayList<Answer> answers;

    public Question(String type, String question,double num_point,String image,ArrayList<Answer> answers){
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Double.compare(question1.num_point, num_point) == 0 &&
                Objects.equals(type, question1.type) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(image, question1.image) &&
                Objects.equals(answers, question1.answers);
    }

}
