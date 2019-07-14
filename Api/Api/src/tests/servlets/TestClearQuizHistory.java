package servlets;

import database.DatabaseManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TestClearQuizHistory extends Mockito {

    HttpServletResponse response;
    HttpServletRequest request ;
    ServletContext context ;
    DatabaseManager manager;
    StringWriter stringWriter;
    PrintWriter writer;
    @Before
    public void set(){
        request = mock(HttpServletRequest.class);
        response =mock(HttpServletResponse.class);
        context = mock(ServletContext.class);
        manager = mock(DatabaseManager.class);


        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test

    public void testGetMethod1() throws IOException, ServletException {

        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("quizId")).thenReturn("1");
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);

        ServletClearQuizHistory servlet = new ServletClearQuizHistory();

        servlet.doPost(request,response);

        verify(manager,atLeast(1)).removeQuizHistory(1);

    }

    @Test

    public void testGetMethod2() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("quizId")).thenReturn("1");
        String notAdmin = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJnaW9yZ2lvIiwidXNlcklkIjoxMCwiaXNBZG1pbiI6ZmFsc2V9" +
                ".kINf6LYhUZ6jf_NGzbowV2jNYLLOM2KFDJyv6KQX2Mk";
        when(request.getHeader("Authorization")).thenReturn(notAdmin);

        ServletClearQuizHistory servlet = new ServletClearQuizHistory();

        servlet.doPost(request,response);
    }
    @Test
    public void testGetMethod3() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("quizId")).thenReturn("1");
        ServletClearQuizHistory servlet = new ServletClearQuizHistory();
        servlet.doPost(request,response);
    }

}
