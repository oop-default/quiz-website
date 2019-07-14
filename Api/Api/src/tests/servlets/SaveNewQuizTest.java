package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Answer;
import models.Question;
import models.Quiz;
import models.Score;
import org.junit.Test;
import parsers.AuthenticationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class SaveNewQuizTest {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        DatabaseManager manager = mock(DatabaseManager.class);
        ServletContext context = mock(ServletContext.class);
        when(context.getAttribute("database")).thenReturn(manager);



        AuthenticationService service = mock(AuthenticationService.class);
        when(service.isAuthenticated()).thenReturn(true);
        when(service.getUserName()).thenReturn("vaxushti");
        when(request.getHeader("Authorization")).thenReturn("smth");
        when(manager.getService("smth")).thenReturn(service);


        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("ias",true));
        questions.add(new Question("QR","ra",5,null,answers));
        Quiz nq = new Quiz(10,"iasnaj","iasias","blabla","vaxushti",1000,questions,"balala");

        Gson gson = new Gson();
        String output = gson.toJson(nq,Quiz.class);
        BufferedReader reader = new BufferedReader(new StringReader(output));

        when(request.getReader()).thenReturn(reader);
        when(manager.saveNewQuiz(nq)).thenReturn(true);

        ServletSaveNewQuiz sgts = new ServletSaveNewQuiz(){
            public ServletContext getServletContext(){
                return context;
            }
        };
        sgts.doPost(request,response);

        verify(manager, atLeast(1)).saveNewQuiz(nq);
    }
}
