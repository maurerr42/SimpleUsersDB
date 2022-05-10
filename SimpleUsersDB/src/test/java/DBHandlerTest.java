import com.indraInterview.db.DBHandler;
import org.junit.jupiter.api.Test;

public class DBHandlerTest {

    DBHandler dbHandler = new DBHandler();

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
}
