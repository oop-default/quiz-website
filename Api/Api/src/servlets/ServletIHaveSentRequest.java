package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import parsers.AuthenticationService;
import responseModels.boolClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletIHaveSentRequest")
public class ServletIHaveSentRequest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        int myId = service.getUserId();

        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        PrintWriter writer = response.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        boolClass requestSent = new boolClass(manager.haveSentRequest(myId, id));
        String output = gson.toJson(requestSent);
        writer.print(output);
    }
}
