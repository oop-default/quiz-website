package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
import parsers.AuthenticationService;
import responseModels.boolClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


import static junit.framework.TestCase.assertEquals;

public class testIHaveSentRequests extends Mockito {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        AuthenticationService service = mock(AuthenticationService.class);
        when(service.isAuthenticated()).thenReturn(true);
        when(service.getUserId()).thenReturn(2);
        when(request.getHeader("Authorization")).thenReturn("ragaca");
        ServletContext context = mock(ServletContext .class);
        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        when(manager.getService("ragaca")).thenReturn(service);


        when(request.getParameter("id")).thenReturn("1");

        when(manager.haveSentRequest(2, 1)).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletIHaveSentRequest tk = new ServletIHaveSentRequest() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        tk.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(new boolClass(true));

        assertEquals(output, stringWriter.toString());
    }

}
