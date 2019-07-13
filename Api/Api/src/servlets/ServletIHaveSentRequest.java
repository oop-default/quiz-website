package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletIHaveSentRequest")
public class ServletIHaveSentRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public class boolClass{
        private boolean sentRequest;
        public boolClass(boolean sentRequest) {
            this.sentRequest = sentRequest;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
        }
        int myId = service.getUserId();
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        ServletOutputStream out = response.getOutputStream();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        boolClass requestSent = new boolClass(manager.haveSentRequest(myId, id));
        String output = gson.toJson(requestSent);
        out.print(output);
    }
}
