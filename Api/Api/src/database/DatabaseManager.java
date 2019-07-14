package database;


import com.mysql.jdbc.ResultSetMetaData;
import parsers.AccountParser;
import parsers.AuthenticationService;
import parsers.QuizParser;
import responseModels.*;
import models.SubmittedQuiz;

import javax.swing.plaf.nimbus.State;
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
    private void updateAchievingsFor(int personID,long updateTime){
        //getting points from here
        String points = "select coalesce(sum(num_points),0) pt from history \n" +
                        "where account_id = "+personID;
        int point = 0;
        ResultSet rspoints = executeQuery(points);

        try {
            rspoints.next();
            point = rspoints.getInt("pt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //if point is 0, zero achievings are added
        if(point == 0)return;
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
                e.printStackTrace();
            }
        }
        //if cant returns
        if(achievementsArr.size() == 0)return;
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
                e.printStackTrace();
            }
        }
        for (int i = 0; i<achievementsArr.size(); i++){
            if(!achievingsArr.contains(achievementsArr.get(i))){
                insertNewAchievementFor(personID,achievementsArr.get(i),updateTime);
            }
        }

    }
    public void saveQuizResults(SubmittedQuiz sq){
        String query = "insert into history(account_id,quiz_id,num_points,time_spent,date_taken) "+
                        "values("+sq.getPersonID()+","+sq.getQuizID()+","+sq.getPoints()+","+sq.getTimeSpent()+","
                        +"FROM_UNIXTIME("+sq.getDateSubmittedMillis()+"))";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateAchievingsFor(sq.getPersonID(),sq.getDateSubmittedMillis());

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
            stmt = con.prepareStatement("select q.id, q.tittle " +
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
            stmt = con.prepareStatement("select * from friend_requests where sender_id = ? and reciever_id = ? and status=?");
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
            String sql = "select q.id, q.tittle, a.id, a.username, count(*) counts\n" +
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
            String sql = "select q.id, q.tittle, a.id, a.username\n" +
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
            String sql = "select q_id, a_id, a_username, q_tittle, h_num_points, h_date\n" +
                    "from (\n" +
                    "select q.id q_id, a.id a_id, a.username a_username, q.tittle q_tittle, h.num_points h_num_points, h.date_taken h_date\n" +
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
                    "select q.id q_id, a.id a_id, a.username a_username, q.tittle q_tittle, h.num_points h_num_points, h.date_taken h_date \n" +
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

    public SearchResponse getSearchResponse(String query) {
        return new SearchResponse(AccountParser.getAccountsByPatrialMatchName(query,this), QuizParser.findQuizByTitle(query,this));
    }

    public AuthenticationService getService(String token) {
        return new AuthenticationService(token);
    }

}
