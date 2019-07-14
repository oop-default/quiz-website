package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
import parsers.AuthenticationService;
import responseModels.friendsQuizzesResponse;
import responseModels.quizzesResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestFriendsTakenQuizzes extends Mockito {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        AuthenticationService service = mock(AuthenticationService.class);
        when(service.isAuthenticated()).thenReturn(true);
        when(service.getUserId()).thenReturn(1);
        when(request.getHeader("Authorization")).thenReturn("ragaca");
        ServletContext context = mock(ServletContext .class);
        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        when(manager.getService("ragaca")).thenReturn(service);


        List<friendsQuizzesResponse> friendsTakenQuizzes = new ArrayList<>();
        friendsQuizzesResponse quiz = new friendsQuizzesResponse(1, 2, "vigaca", "ragaca", 50);
        friendsTakenQuizzes.add(quiz);
        when(manager.getFriendsQuizActivity(1)).thenReturn(friendsTakenQuizzes);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletFriendsQuizActivity tk = new ServletFriendsQuizActivity() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        tk.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(friendsTakenQuizzes);

        assertEquals(output, stringWriter.toString());
    }
}
