package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Score;
import org.junit.Test;
import responseModels.FriendAchievements;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendAchievementsTest {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        DatabaseManager manager = mock(DatabaseManager.class);
        ServletContext context = mock(ServletContext.class);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("id")).thenReturn("1");
        ArrayList<FriendAchievements> achievements = new ArrayList<>();
        achievements.add(new FriendAchievements(3,"vaxo","Newbie"));

        when(manager.getAchievementsFor(1)).thenReturn(achievements);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        ServletFriendsAchievements sfa = new ServletFriendsAchievements(){
            public ServletContext getServletContext(){
                return context;
            }
        };
        sfa.doGet(request,response);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String output = gson.toJson(achievements,ArrayList.class);

        assertEquals(output,stringWriter.toString());
    }
}
