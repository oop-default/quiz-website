package servlets;

import Jwt.JwtManager;
import com.google.gson.Gson;
import cracker.PasswordGenerator;
import database.DatabaseManager;
import models.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parsers.AccountParser;
import responseModels.LoginRegisterResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class TestServletRegister extends Mockito {
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

    public void testPostMethod1() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);


        Account account = new Account("","12345","giorgi"
                ,"jikia","MALE");
        Gson gson = new Gson();
        String test =  gson.toJson(account,Account.class);

        String hashedPassword = PasswordGenerator.generate(account.getPassword());

        Reader inputString = new StringReader(test);
        BufferedReader reader = new BufferedReader(inputString);
        when(request.getReader()).thenReturn(reader);
        when(manager.validUser("jiko",hashedPassword)).thenReturn(false);
        String message = "Incorrect input!";
        LoginRegisterResponse response1 = new LoginRegisterResponse(400,null,message);
        String toString = gson.toJson(response1);

        ServletRegister myServlet = new ServletRegister();
        myServlet.doPost(request,response);
        String result = stringWriter.toString();
        assertEquals(toString,result);

    }
    @Test
    public void testPostMethod2() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);


        Account account = new Account("jiko","12345","giorgi"
                ,"jikia","MALE");
        Gson gson = new Gson();
        String test =  gson.toJson(account,Account.class);

        String hashedPassword = PasswordGenerator.generate(account.getPassword());

        Reader inputString = new StringReader(test);
        BufferedReader reader = new BufferedReader(inputString);
        when(request.getReader()).thenReturn(reader);
        when(manager.usernameExists("jiko")).thenReturn(true);
        String message = "Username already exists!";
        LoginRegisterResponse response1 = new LoginRegisterResponse(406,null,message);
        String toString = gson.toJson(response1);

        ServletRegister myServlet = new ServletRegister();
        myServlet.doPost(request,response);
        String result = stringWriter.toString();
        assertEquals(toString,result);
    }

    @Test

    public void testPostMethod3() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);


        Account account = new Account("jiko","12345","giorgi"
                ,"jikia","MALE");
        Gson gson = new Gson();
        String test =  gson.toJson(account,Account.class);

        Reader inputString = new StringReader(test);
        BufferedReader reader = new BufferedReader(inputString);
        when(request.getReader()).thenReturn(reader);
        when(manager.usernameExists("jiko")).thenReturn(false);
        when(manager.getUserId("jiko")).thenReturn(1);
        String message = "Ok";
        String jws = null;
        account.setPassword(PasswordGenerator.generate(account.getPassword()));
        jws = JwtManager.createJWS(account.getUsername(), 1,false);
        LoginRegisterResponse response1 = new LoginRegisterResponse(200,jws,message);
        String toString = gson.toJson(response1);

        ServletRegister myServlet = new ServletRegister();
        myServlet.doPost(request,response);
        String result = stringWriter.toString();
        assertEquals(toString,result);

        verify(manager,atLeast(1)).insertAccount(account);
    }
}
