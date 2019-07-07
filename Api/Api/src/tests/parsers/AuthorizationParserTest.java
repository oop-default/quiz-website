package parsers;

import database.DatabaseManager;
import models.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorizationParserTest {
    DatabaseManager manager;
    Account account;
    Account account1;
    Account account2;
    @Before
    public void setUp() throws Exception {
        manager = new DatabaseManager();
        account = new Account("jiko","1234",
                "giorgi","jikia","Male");
        account1=new Account("jiksona","5678",
                "gio","jikia","Male");
        account2=new Account("jikk","91011",
                "giushki","jikia","Male");
    }
    @Test
    public void insertAccount() {
        AuthorizationParser.insertAccount(account,manager);
        AuthorizationParser.insertAccount(account1,manager);
        AuthorizationParser.insertAccount(account2,manager);
    }
    @Test
    public void validUser() {
        assertTrue(AuthorizationParser.validUser("jiko","1234",manager));
        assertFalse(AuthorizationParser.validUser("abdula","1234",manager));
        assertFalse(AuthorizationParser.validUser("jikk","djjaj",manager));
    }
    @Test
    public void usernameExists() {
        assertTrue(AuthorizationParser.usernameExists("jikk",manager));
        assertTrue(AuthorizationParser.usernameExists("jiksona",manager));
        assertTrue(AuthorizationParser.usernameExists("jiko",manager));
        assertFalse(AuthorizationParser.usernameExists("abdula",manager));
    }
}