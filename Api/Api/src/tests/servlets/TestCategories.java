package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
import responseModels.CategoriesResponse;
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

public class TestCategories extends Mockito {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ServletContext context = mock(ServletContext .class);

        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        List<CategoriesResponse> categories = new ArrayList<>();
        CategoriesResponse category = new CategoriesResponse(1, "imnairi");
        categories.add(category);
        when(manager.getCategories()).thenReturn(categories);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletCategories tk = new ServletCategories() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        tk.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(categories);

        assertEquals(output, stringWriter.toString());

    }


}
