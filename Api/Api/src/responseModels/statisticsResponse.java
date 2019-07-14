package responseModels;

public class statisticsResponse {
    private int registeredInOneDay;
    private int registeredInOneMonth;
    private int registeredInOneYear;
    private int totalUsers;
    private int writtenQuizzesInOneDay;
    private int writtenQuizzesInOneMonth;
    private int writtenQuizzesInOneYear;
    private int totalWrittenQuizzes;

    public void setRegisteredInOneDay(int registeredInOneDay) {
        this.registeredInOneDay = registeredInOneDay;
    }

    public void setTotalWrittenQuizzes(int totalWrittenQuizzes) {
        this.totalWrittenQuizzes = totalWrittenQuizzes;
    }

    public void setRegisteredInOneYear(int registeredInOneYear) {
        this.registeredInOneYear = registeredInOneYear;
    }

    public void setWrittenQuizzesInOneMonth(int writtenQuizzesInOneMonth) {
        this.writtenQuizzesInOneMonth = writtenQuizzesInOneMonth;
    }

    public void setWrittenQuizzesInOneYear(int writtenQuizzesInOneYear) {
        this.writtenQuizzesInOneYear = writtenQuizzesInOneYear;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public void setWrittenQuizzesInOneDay(int writtenQuizzesInOneDay) {
        this.writtenQuizzesInOneDay = writtenQuizzesInOneDay;
    }

    public void setRegisteredInOneMonth(int registeredInOneMonth) {
        this.registeredInOneMonth = registeredInOneMonth;
    }
}
