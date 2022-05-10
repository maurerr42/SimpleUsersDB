import com.indraInterview.db.DBHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DBHandlerTest {

    DBHandler dbHandler = new DBHandler();

    @BeforeAll
    static void initTests() {
        DBHandler dbHandler = new DBHandler();
        dbHandler.deleteAll();
    }

    @Test
    void addUserSuccess() {
        dbHandler.add(1, "111", "Jano");
    }

    @Test
    void addUserNullUserGuid() {
        dbHandler.add(1, null, "Jano");
    }

    @Test
    void addUserEmptyUserGuid() {
        dbHandler.add(1, "", "Jano");
    }

    @Test
    void addUserNullUserName() {
        dbHandler.add(1, "111", null);
    }

    @Test
    void addUserEmptyUserName() {
        dbHandler.add(1, "111", "");
    }

    @Test
    void printAllSuccess() {
        dbHandler.printAll();
    }

    @Test
    void deleteAllSuccess() {
        dbHandler.deleteAll();
    }

    @Test
    void addUserAndPrintAllDeleteAll_1() {
        dbHandler.deleteAll();
        dbHandler.add(1, "111", "Jano");

        Assertions.assertEquals("USER_ID: 1, USER_GUID: 111, USER_NAME: Jano", dbHandler.printAll());
    }

    @Test
    void addUserAndPrintAllDeleteAll_2() {
        dbHandler.deleteAll();
        dbHandler.add(1, "111", "Jano");
        dbHandler.add(2, "38a", "Fero");
        dbHandler.add(3, "abc", "Stanislav");

        Assertions.assertEquals("USER_ID: 1, USER_GUID: 111, USER_NAME: Jano\nUSER_ID: 2, USER_GUID: 38a, USER_NAME: Fero\nUSER_ID: 3, USER_GUID: abc, USER_NAME: Stanislav", dbHandler.printAll());
    }

    @Test
    void addUserAndPrintAllDeleteAll_3() {
        dbHandler.deleteAll();
        dbHandler.add(1, "111", "Jano");
        dbHandler.add(2, "38a", "Fero");
        dbHandler.add(3, "abc", "Stanislav");
        dbHandler.deleteAll();

        Assertions.assertEquals("Zero rows found", dbHandler.printAll());
    }
}
