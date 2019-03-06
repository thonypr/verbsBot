import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }

    public static void addUser(Long userId, State state) {
        if (userId == null || state == null) {
            Notificator.sendToAdmin("addUser - ERROR: userId = " + userId + "; state = " + state);
        } else {
            PreparedStatement stm = null;
            try {
                stm = getConnection().prepareStatement("INSERT INTO tg_user VALUES (?,?);");
                stm.setLong(1, userId);
                stm.setString(2, state.toString());
                stm.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                Notificator.sendToAdmin("addUser - ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
