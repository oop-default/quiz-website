package database;


import com.mysql.jdbc.ResultSetMetaData;
import models.*;
import parsers.AchievementsParser;
import parsers.AuthenticationService;
import parsers.ScoreParser;
import responseModels.FriendAchievements;
import responseModels.NotPersonalScoreResponse;
import responseModels.friendsQuizzesResponse;
import responseModels.quizzesResponse;
import parsers.AccountParser;
import parsers.AuthenticationService;
import parsers.QuizParser;
import responseModels.*;
import models.SubmittedQuiz;

import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static  String account = MyDBInfo.MYSQL_USERNAME;
    static  String password = MyDBInfo.MYSQL_PASSWORD;
    static  String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    static  String database = MyDBInfo.MYSQL_DATABASE_NAME;

    private Connection con;
    private Statement stmt;
    private ResultSetMetaData rsmd;


    public DatabaseManager(){
        rsmd = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection( "jdbc:mysql://" + server+"?useSSL=false", account ,password);
            stmt = con.createStatement();
            stmt.executeQuery("USE " + database);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query){
        try {
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet executeGetQuery(String query) throws SQLException {
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }

    public void executeUpdateQuery(String query) throws SQLException {
        stmt.executeUpdate(query);
    }

    private void insertNewAchievementFor(int personID,int achievingID,long updateTime){
        String query = "insert into achievings(achievment_id,account_id,date_achieved) values ("+achievingID+
                ","+personID+","+
                "FROM_UNIXTIME("+updateTime+"))";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean updateAchievingsFor(int personID,long updateTime){
        //getting points from here
        String points = "select coalesce(sum(num_points),0) pt from history \n" +
                        "where account_id = "+personID;
        int point = 0;
        ResultSet rspoints = executeQuery(points);

        try {
            rspoints.next();
            point = rspoints.getInt("pt");
        } catch (SQLException e) {
            return false;
        }
        //if point is 0, zero achievings are added
        if(point == 0)return false;;
        //getting total achievements player can take
        String achievements = "select id,num_points from achievements";
        ResultSet rsachievements = executeQuery(achievements);
        ArrayList<Integer> achievementsArr = new ArrayList<>();
        while(true){
            try {
                if (!rsachievements.next()) break;
                int id = rsachievements.getInt("id");
                int num_points = rsachievements.getInt("num_points");
                if(num_points <= point) {
                    achievementsArr.add(id);
                }
            } catch (SQLException e) {
                return false;
            }
        }
        //if cant returns
        if(achievementsArr.size() == 0)return false;;
        //older achievings
        String personalAchievings = "select achievment_id from achievings \n" +
                                    "where account_id = "+personID;
        ResultSet rsachievings = executeQuery(personalAchievings);
        ArrayList<Integer> achievingsArr = new ArrayList<>();
        while(true){
            try {
                if (!rsachievings.next()) break;
                int id = rsachievings.getInt("achievment_id");
                achievingsArr.add(id);
            } catch (SQLException e) {
                return false;
            }
        }
        for (int i = 0; i<achievementsArr.size(); i++){
            if(!achievingsArr.contains(achievementsArr.get(i))){
                insertNewAchievementFor(personID,achievementsArr.get(i),updateTime);
            }
        }
        return true;
    }

    private void insertAnswer(Answer answer,int pos){
        String query = "insert into answers(answer,is_correct,question_id) values('"+answer.getAnswer()+"',"
                        +answer.isCorrect()+","+pos+")";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQuestion(Question question,int pos){
        String query = "insert into questions(type,question,quiz_id,num_points,image) values('"+question.getType()+"','"+question.getQuestion()
                        +"',"+pos+","+question.getNum_poin()+",'"+question.getImage()+"')";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveQuizResults(SubmittedQuiz sq){
        String query = "insert into history(account_id,quiz_id,num_points,time_spent,date_taken) "+
                        "values("+sq.getPersonID()+","+sq.getQuizID()+","+sq.getPoints()+","+sq.getTimeSpent()+","
                        +"FROM_UNIXTIME("+sq.getDateSubmittedMillis()+"))";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }
        return updateAchievingsFor(sq.getPersonID(),sq.getDateSubmittedMillis());
    }
    public boolean saveNewQuiz(Quiz nq){
       String description =  nq.getDescription();
       String title = nq.getTitle();
       String author = nq.getAuthor();
       int num_points = nq.getNum_points();
       ArrayList<Question> questionData = nq.getQuestions();
       String type = nq.getType();

       System.out.println("title: "+title+" author : "+author+" description : "+description+" num_points : "+num_points
                            + "questions? "+questionData.size());
       if(type == null ||title == null || author == null || title.length() == 0
               || type.length() == 0 || author.length() == 0 || questionData.size() == 0
                    || num_points == 0){
           return false;
       }
        System.out.println("gaiara bazisi test");
       //get userID
        ResultSet rsuserid = executeQuery("select count(*) count,id from accounts\n" +
                                          "where username = '"+author+"'");
        int authorID = 0;
        try {
            rsuserid.next();
            int count = rsuserid.getInt("count");
            if(count == 0)return false;

            authorID = rsuserid.getInt("id");
        } catch (SQLException e) {
            return false;
        }
        //
        System.out.println("gaiara authorID test");
        //get typeID
        ResultSet rstypeid = executeQuery("select count(*) count,id from categories\n" +
                "where category = '"+type+"'");
        int typeID = 0;
        try {
            rstypeid.next();
            int count = rstypeid.getInt("count");
            if(count == 0)return false;
            typeID = rstypeid.getInt("id");
        } catch (SQLException e) {
            return false;
        }
        System.out.println("gaiara typeID test");
        //
       int addedQuestions = 0;
       for(int i = 0; i<questionData.size(); i++){
           Question question = questionData.get(i);
           String questionString = question.getQuestion();
           ArrayList<Answer> answers = question.getAnswers();
           String img = question.getImage();
           double points = question.getNum_poin();
           String questionType = question.getType();

           if(questionString == null || questionString.length() == 0 || points == 0
                || questionType == null || questionType.length() == 0 || (!questionType.equals("QR")
                   && !questionType.equals("MC") && !questionType.equals("PR") && !questionType.equals("FB"))){
               continue;
           }
           System.out.println("gaiara question bazisi test");
           if(answers == null || answers.size() == 0)continue;

           int addedAnswers = 0;
           for(int j = 0; j<answers.size(); j++) {
               Answer answer = answers.get(j);
               String answerString = answer.getAnswer();
               if (answerString == null || answerString.length() == 0) continue;

               //add answer
               ResultSet rsquestionpos = executeQuery("select coalesce(count(*),0) pos from questions");
               int questionpos = 0;
               try {
                   rsquestionpos.next();
                   questionpos = rsquestionpos.getInt("pos");
               } catch (SQLException e) {
                   return false;
               }
               //
               insertAnswer(answer, questionpos + 1);
               addedAnswers++;
           }
           if(addedAnswers == 0)continue;
           System.out.println("gaiara answer test");

           ResultSet rsquizpos = executeQuery("select coalesce(count(*),0) pos from quizzes");
           int quizpos = 0;
           try {
               rsquizpos.next();
               quizpos = rsquizpos.getInt("pos");
           } catch (SQLException e) {
               return false;
           }
            //add question
           insertQuestion(question,quizpos+1);
           addedQuestions++;
       }
       if(addedQuestions == 0)return false;
        System.out.println("gaiara question chamateba test");
       //add quizData;
        String query= "insert into quizzes(title,author_id,description,"+"" +
                      "date_created,category_id,num_points) values('"+title+"',"+authorID+",'"+description+
                      "',sysdate(),"+typeID+","+num_points+")";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }
        System.out.println("gaiara quizz chamateba test");
        return true;
    }

    public List<quizzesResponse> getCreatedQuizzes(int id) {
        List<quizzesResponse> quizzes = new ArrayList<>();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("select * from quizzes where author_id = ?");
            stmt.setInt(1, id);

            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                quizzesResponse quiz = new quizzesResponse(rs.getInt(1), rs.getString(2));
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        quizzesResponse quiz = new quizzesResponse(1, "Sad");
        quizzes.add(quiz);

        return quizzes;
    }

    public List<quizzesResponse> getTakenQuizzes(int id) {
        List<quizzesResponse> quizzes = new ArrayList<>();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("select q.id, q.title " +
                    "from quizzes q " +
                    "join history h " +
                    "on q.id = h.quiz_id " +
                    "where h.account_id = ?");
            stmt.setInt(1, id);
            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                quizzesResponse quiz = new quizzesResponse(rs.getInt(1), rs.getString(2));
                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        quizzesResponse quiz = new quizzesResponse(1, "Sad");
        quizzes.add(quiz);

        return quizzes;
    }

    public boolean haveSentRequest(int myId, int receiverId) {
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("select * from friend_requests where sender_id = ? and receiver_id = ? and status=?");
            stmt.setInt(1, myId);
            stmt.setInt(2, receiverId);
            stmt.setString(3, "pending");
            ResultSet rs;
            rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<quizzesResponse> getPopQuizzes() {
        List<quizzesResponse> res = new ArrayList<>();
        PreparedStatement stmt;
        try {
            String sql = "select q.id, q.title, a.id, a.username, count(*) counts\n" +
                    "from quizzes q\n" +
                    "join history h\n" +
                    "on q.id = h.quiz_id\n" +
                    "join accounts a\n" +
                    "on a.id = q.author_id\n" +
                    "group by quiz_id\n" +
                    "order by counts desc\n";
            stmt = con.prepareStatement(sql);

            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                quizzesResponse quiz = new quizzesResponse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                res.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        quizzesResponse quiz = new quizzesResponse(3167, "ragaca", 3, "vigaca");
        res.add(quiz);
        return res;
    }

    public List<quizzesResponse> getRecQuizzes() {
        List<quizzesResponse> recQuizzes = new ArrayList<>();
        PreparedStatement stmt;
        try {
            String sql = "select q.id, q.title, a.id, a.username\n" +
                    "from quizzes q\n" +
                    "join accounts a\n" +
                    "on a.id = q.author_id\n" +
                    "order by q.date_created desc\n";
            stmt = con.prepareStatement(sql);

            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                quizzesResponse quiz = new quizzesResponse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                recQuizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        quizzesResponse quiz = new quizzesResponse(3167, "ragacxjna", 312, "vigaca");
        recQuizzes.add(quiz);
        return recQuizzes;
    }

    public statisticsResponse getStatistics() {
        PreparedStatement stmt;
        statisticsResponse st;
        try {
            String sql = "select count(*) from accounts a\n" +
                    "where a.date_created >= date_sub(now(), interval 1 day)\n" +
                    "union all\n" +
                    "select count(*) from accounts a\n" +
                    "where a.date_created >= date_sub(now(), interval 1 month)\n" +
                    "union all\n" +
                    "select count(*) from accounts a\n" +
                    "where a.date_created >= date_sub(now(), interval 1 year)\n" +
                    "union all\n" +
                    "select count(*) from accounts a\n" +
                    "union all\n" +
                    "select count(*) from history h\n" +
                    "where h.date_taken >= date_sub(now(), interval 1 day)\n" +
                    "union all\n" +
                    "select count(*) from history h\n" +
                    "where h.date_taken >= date_sub(now(), interval 1 month)\n" +
                    "union all\n" +
                    "select count(*) from history h\n" +
                    "where h.date_taken >= date_sub(now(), interval 1 year)\n" +
                    "union all\n" +
                    "select count(*) from history h;";
            stmt = con.prepareStatement(sql);

            ResultSet rs;
            rs = stmt.executeQuery(sql);
            st = new statisticsResponse();
            for(int i = 1; i <= 8; i++) {
                rs.next();
                switch (i) {
                    case 1:
                        st.setRegisteredInOneDay(rs.getInt(1));
                        break;
                    case 2:
                        st.setRegisteredInOneMonth(rs.getInt(1));
                        break;
                    case 3:
                        st.setRegisteredInOneYear(rs.getInt(1));
                        break;
                    case 4:
                        st.setTotalUsers(rs.getInt(1));
                        break;
                    case 5:
                        st.setWrittenQuizzesInOneDay(rs.getInt(1));
                        break;
                    case 6:
                        st.setWrittenQuizzesInOneMonth(rs.getInt(1));
                        break;
                    case 7:
                        st.setWrittenQuizzesInOneYear(rs.getInt(1));
                        break;
                    case 8:
                        st.setTotalWrittenQuizzes(rs.getInt(1));
                }
            }
            return st;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<friendsQuizzesResponse> getFriendsQuizActivity(int id) {
        List<friendsQuizzesResponse> quizzes = new ArrayList<>();

        PreparedStatement stmt;
        try {
            String sql = "select q_id, a_id, a_username, q_title, h_num_points, h_date\n" +
                    "from (\n" +
                    "select q.id q_id, a.id a_id, a.username a_username, q.title q_title, h.num_points h_num_points, h.date_taken h_date\n" +
                    "from history h \n" +
                    "join quizzes q \n" +
                    "on q.id = h.quiz_id \n" +
                    "join friends f \n" +
                    "on f.second_id = h.account_id \n" +
                    "join accounts a \n" +
                    "on a.id = f.second_id \n" +
                    "where h.account_id in \n" +
                    "(select second_id from friends where first_id=?) \n" +
                    "UNION \n" +
                    "select q.id q_id, a.id a_id, a.username a_username, q.title q_title, h.num_points h_num_points, h.date_taken h_date \n" +
                    "from history h \n" +
                    "join quizzes q \n" +
                    "on q.id = h.quiz_id \n" +
                    "join friends f \n" +
                    "on f.first_id = h.account_id \n" +
                    "join accounts a \n" +
                    "on a.id = f.first_id \n" +
                    "where h.account_id in \n" +
                    "(select first_id from friends where second_id=?)) friends_quizzes\n" +
                    "order by h_date desc;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                friendsQuizzesResponse quiz = new friendsQuizzesResponse(rs.getInt(1),
                        rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        friendsQuizzesResponse quiz = new friendsQuizzesResponse(1, 1, "vigaca", "ragaca", 100);
        quizzes.add(quiz);

        return quizzes;
    }

    public List<CategoriesResponse> getCategories() {
        List res = new ArrayList<>();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("select * from categories");

            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()) {
                CategoriesResponse category = new CategoriesResponse(rs.getInt(1), rs.getString(2));
                res.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CategoriesResponse cat = new CategoriesResponse(1, "imnairi");
        res.add(cat);
        return res;
    }

    public Connection getConnection(){
        return con;
    }
    public ArrayList<Score> getGlobalTopScores(int quizID){
        return ScoreParser.getGlobalTopScores(quizID,this);
    }
    public ArrayList<Score> getFriendTopScores(int id,int quizID){
        return ScoreParser.getFriendTopScores(id,quizID,this);
    }
    public ArrayList<Score> getPersonalTopScores(int id,int quizID){
       return ScoreParser.getPersonalTopScores(id,quizID,this);
    }
    public ArrayList<FriendAchievements> getAchievementsFor(int id){
        return AchievementsParser.getAchievementsFor(id,this);
    }

    public SearchResponse getSearchResponse(String query) {
        return new SearchResponse(AccountParser.getAccountsByPatrialMatchName(query,this), QuizParser.findQuizByTitle(query,this));
    }

    public AuthenticationService getService(String token) {
        return new AuthenticationService(token);
    }

}
