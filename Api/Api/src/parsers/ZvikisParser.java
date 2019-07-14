package parsers;
import com.google.gson.Gson;
import responseModels.ChallengeParse;
import responseModels.FriendRequestParse;
import responseModels.NoteParse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZvikisParser {
    private static Gson gson = new Gson();

    public static void challengesToJsonArray(PrintWriter writer, ResultSet rs)
            throws IOException, SQLException {
        ArrayList<ChallengeParse> challengeParses = getChallengeObjects(rs);
        writer.print(gson.toJson(challengeParses, ArrayList.class));
    }

    private static ArrayList getChallengeObjects(ResultSet rs) throws SQLException {
        ArrayList<ChallengeParse> challengeParses = new ArrayList<ChallengeParse>();
        while (rs.next()) {
            int fromId = rs.getInt("fromId");
            int quizId = rs.getInt("quizId");
            String fromName = rs.getString("fromName");
            challengeParses.add(new ChallengeParse(fromId, fromName, quizId));
        }
        return challengeParses;
    }

    public static void friendRequestParseToJsonArray(PrintWriter writer, ResultSet rs)
            throws IOException, SQLException {
        ArrayList<FriendRequestParse> challenges = getFriendRequestObjects(rs);
        writer.print(gson.toJson(challenges, ArrayList.class));
    }

    private static ArrayList getFriendRequestObjects(ResultSet rs) throws SQLException {
        ArrayList<FriendRequestParse> friendRequestParse = new ArrayList<FriendRequestParse>();

        while (rs.next()) {
            int fromId = rs.getInt("fromId");
            String fromName = rs.getString("fromName");
            friendRequestParse.add(new FriendRequestParse(fromName, fromId));
        }
        return friendRequestParse;
    }

    public static void notesToJsonArray(PrintWriter writer, ResultSet rs)
            throws IOException, SQLException {
        ArrayList<NoteParse> noteParses = getNoteObjects(rs);
        writer.print(gson.toJson(noteParses, ArrayList.class));
    }

    private static ArrayList getNoteObjects(ResultSet rs) throws SQLException {
        ArrayList<NoteParse> noteParses = new ArrayList<NoteParse>();

        while (rs.next()) {
            int fromId = rs.getInt("fromId");
            String fromName = rs.getString("fromName");
            String note = rs.getString("note");
            noteParses.add(new NoteParse(fromId, fromName, note));
        }
        return noteParses;
    }
}
