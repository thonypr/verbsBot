import java.net.URISyntaxException;
import java.sql.*;
import java.util.HashMap;

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
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                Notificator.sendToAdmin("addUser - ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void updateUser(Long userId, State state) {
        if (userId == null || state == null) {
            Notificator.sendToAdmin("updateUser - ERROR: userId = " + userId + "; state = " + state);
        } else {
            PreparedStatement stm = null;
            try {
                stm = getConnection().prepareStatement("UPDATE tg_user SET state = ? WHERE id = ?");
                stm.setLong(2, userId);
                stm.setString(1, state.toString());
                stm.execute();
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                Notificator.sendToAdmin("updateUser - ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static HashMap<Long, User> getUsers() {

        HashMap<Long, User> users = new HashMap<>();
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement("SELECT * FROM tg_user;");
            ResultSet resultSet = stm.executeQuery();
            // process the results
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1), State.valueOf(resultSet.getString(2)));
                users.put(user.getUserId(), user);
            }
            resultSet.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            Notificator.sendToAdmin("getUsers - ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }
}
