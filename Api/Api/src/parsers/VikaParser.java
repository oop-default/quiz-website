package VikasModels;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class VikaParser {

    private static Gson gson = new Gson();

    public static void resultSetToJsonArrayFancy(String status, JsonWriter writer, final ResultSet rs) throws IOException {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int cc = meta.getColumnCount();

            writer.beginArray();
            Class<?> typeStatus = Class.forName(status.getClass().getName());
            gson.toJson(status, typeStatus, writer);

            while (rs.next()) {
                writer.beginObject();
                for (int i = 1; i <= cc; ++i) {
                    writer.name(meta.getColumnName(i));
                    Class<?> type = Class.forName(meta.getColumnClassName(i));
                    gson.toJson(rs.getObject(i), type, writer);
                }
                writer.endObject();
            }
            writer.endArray();
        } catch (SQLException e) {
            throw new RuntimeException(e.getClass().getName(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getClass().getName(), e);
        }
    }

    public static void resultSetToJsonArray(JsonWriter writer, final ResultSet rs) throws IOException {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int cc = meta.getColumnCount();

            writer.beginArray();

            while (rs.next()) {
                writer.beginObject();
                for (int i = 1; i <= cc; ++i) {
                    writer.name(meta.getColumnName(i));
                    Class<?> type = Class.forName(meta.getColumnClassName(i));
                    gson.toJson(rs.getObject(i), type, writer);
                }
                writer.endObject();
            }
            writer.endArray();
        } catch (SQLException e) {
            throw new RuntimeException(e.getClass().getName(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getClass().getName(), e);
        }
    }

    public static UserInfo parseUserInfo(final ResultSet rs) throws SQLException {
        rs.next();
        String userName = rs.getString("username");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String image = rs.getString("image");
        boolean isAdmin = rs.getBoolean("is_admin");
        UserInfo userInfo = new UserInfo(firstName, lastName, userName, image, null, isAdmin);
        return userInfo;
    }
}
