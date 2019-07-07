package responseModels;

import models.Account;
import models.Quiz;

import java.util.ArrayList;

public class SearchResponse {
    private ArrayList<Account> accounts;
    private ArrayList<Quiz> quizzes;
    public SearchResponse(ArrayList<Account> accounts,ArrayList<Quiz> quizzes){
        this.quizzes=quizzes;
        this.accounts=accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
