package servlets;

import Jwt.JwtManager;
import parsers.AuthenticationService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "ServletProfileImage")
public class ServletProfileImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        String username = service.getUserName();
        String fileName=username+".jpg";
        ServletInputStream in = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
            System.out.println("here");
        }
        System.out.println("1");
        byte[] bytesArray = os.toByteArray();
        System.out.println("2");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytesArray);
        System.out.println("3");

        BufferedImage bImage2 = ImageIO.read(bis);
        System.out.println("4");

        ImageIO.write(bImage2, "jpg",
                new File("C:\\Users\\Giorgi\\Desktop\\quiz-website\\quiz-website" +
                        "\\Api\\Api\\web\\Images\\Profile\\"+fileName));
        System.out.println("5");
        System.out.println("image created");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
