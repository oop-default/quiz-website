package servlets;

import database.DatabaseManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestRemoveAccount extends Mockito {
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
    public void testPostMethod1() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("username")).thenReturn("jiko");
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        ServletRemoveAccount servlet = new ServletRemoveAccount();
        servlet.doPost(request,response);
        verify(manager,atLeast(1)).removeAccount("jiko");
    }

    @Test
    public void testPostMethod2() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("username")).thenReturn("jiko");
        ServletRemoveAccount servlet = new ServletRemoveAccount();
        servlet.doPost(request,response);
    }

    @Test
    public void testPostMethod3() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("username")).thenReturn("jiko");
        String notAdmin = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJnaW9yZ2lvIiwidXNlcklkIjoxMCwiaXNBZG1pbiI6ZmFsc2V9" +
                ".kINf6LYhUZ6jf_NGzbowV2jNYLLOM2KFDJyv6KQX2Mk";
        when(request.getHeader("Authorization")).thenReturn(notAdmin);
        ServletRemoveAccount servlet = new ServletRemoveAccount();
        servlet.doPost(request,response);
    }
}
