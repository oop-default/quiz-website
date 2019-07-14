package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Announcement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parsers.AuthenticationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestServletAnnouncement extends Mockito {
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
    /*
        test when user is loged in and authorized.
     */
    @Test
    public void testGetMethod1() throws IOException, ServletException, SQLException {

        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);

        ArrayList<Announcement> announcements = getAnnouncemetns();
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        when(manager.getAllAnnouncement()).thenReturn(announcements);
        Gson gson = new Gson();
        String toString = gson.toJson(announcements, ArrayList.class);
        ServletAnnouncement myServlet = new ServletAnnouncement();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals(result,toString);

    }
    /*
        test not valid  request.
     */
    @Test
    public void testGetMethod2() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        String notAdmin = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJnaW9yZ2lvIiwidXNlcklkIjoxMCwiaXNBZG1pbiI6ZmFsc2V9" +
                ".kINf6LYhUZ6jf_NGzbowV2jNYLLOM2KFDJyv6KQX2Mk";
        when(request.getHeader("Authorization")).thenReturn(notAdmin);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        ServletAnnouncement myServlet = new ServletAnnouncement();
        myServlet.doGet(request,response);
    }
    @Test
    public void testGetMethod3() throws IOException, ServletException {

        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        ServletAnnouncement myServlet = new ServletAnnouncement();
        myServlet.doGet(request,response);
    }

    /*

     */
    @Test

    public void testPostMethod1() throws SQLException, IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        ArrayList<Announcement> announcements = getAnnouncemetns();
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";

        String something = "aq weria ragac";


        String test = "{\"announcement\":\"this is\"}";

        Reader inputString = new StringReader(test);
        BufferedReader reader = new BufferedReader(inputString);

        when(request.getReader()).thenReturn(reader);
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        when(manager.getAllAnnouncement()).thenReturn(announcements);
        ServletAnnouncement myServlet = new ServletAnnouncement();
        myServlet.doPost(request,response);
        verify(manager,atLeast(1)).addNewAnnouncement(7,"jikssi","this is");
    }

    @Test

    public void testPostMethod2() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        ServletAnnouncement myServlet = new ServletAnnouncement();
        myServlet.doPost(request,response);
    }

    private ArrayList<Announcement> getAnnouncemetns(){
        Announcement announcement = new Announcement();
        announcement.setAnnouncement("this is my first announcement in this page Giorgi jikia");
        ArrayList<Announcement> announcements =new ArrayList<>();
        announcement.setId(1);
        announcement.setAuthor_id(7);
        announcement.setAuthor("jikssi");
        announcements.add(announcement);
        return announcements;
    }

}
