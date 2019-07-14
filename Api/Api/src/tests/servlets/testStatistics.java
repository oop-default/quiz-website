package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
import responseModels.quizzesResponse;
import responseModels.statisticsResponse;

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

public class testStatistics extends Mockito {

    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext context = mock(ServletContext .class);

        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        statisticsResponse statistics = new statisticsResponse();
        statistics.setTotalWrittenQuizzes(1);
        statistics.setWrittenQuizzesInOneYear(1);
        statistics.setWrittenQuizzesInOneMonth(1);
        statistics.setWrittenQuizzesInOneDay(1);
        statistics.setTotalUsers(1);
        statistics.setRegisteredInOneYear(1);
        statistics.setRegisteredInOneMonth(1);
        statistics.setRegisteredInOneDay(1);
        when(manager.getStatistics()).thenReturn(statistics);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletStatistics st = new ServletStatistics() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        st.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(statistics);

        assertEquals(output, stringWriter.toString());
    }
}
