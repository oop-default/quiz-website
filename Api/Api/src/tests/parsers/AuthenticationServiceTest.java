package parsers;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    @Test
    public void isAuthenticated() {
        AuthenticationService service =
                new AuthenticationService("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaWtzc2kifQ.yZk0NjRrKjSPJ15HwbX5ELLnHrMoCaWSISyQAJUPZEk");
        assertTrue(service.isAuthenticated());
    }

    @Test
    public void getUserName() {
        AuthenticationService service =
                new AuthenticationService("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaWtzc2kifQ.yZk0NjRrKjSPJ15HwbX5ELLnHrMoCaWSISyQAJUPZEk");
        if(service.isAuthenticated()){
            assertEquals("jikssi",service.getUserName());
        }
    }

    @Test
    public void isAdmin() {
    }
}