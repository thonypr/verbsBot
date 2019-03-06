import java.util.HashMap;

public class UsersController {

    private static HashMap<Long, User> users = new HashMap<>();

    public static void setUsers(HashMap<Long, User> dbUsers) {
        HashMap<Long, User> tmp = new HashMap<>();
        tmp.putAll(users);
        tmp.putAll(dbUsers);
        users = tmp;
        Notificator.sendToAdmin("setUsers - successfully loaded " + users.values().size() + " users!");
    }

    public static String getUsers() {
        return users.toString();
    }

    public static User getUser(Long userId) {
        if(users.containsKey(userId)) {
            return users.get(userId);
        }
        //TODO: npe
        return null;
    }

    public static boolean hasUser(Long userId) {
        return users.containsKey(userId);
    }

    public static void addUser(Long userId) {
        if(users.containsKey(userId)) {
            // we have him here
            JamiumBot.log(userId.toString(), "We got him!", "");
        }
        else {
            User newUser = new User(userId, State.WELCOME);
            users.put(userId, newUser);
            JamiumBot.log(userId.toString(), "Collected!", "");
        }
    }

    public static void updateUserState(Long userId, State state) {
        if(users.containsKey(userId)) {
            User newUser = new User(userId, state);
            users.replace(userId, newUser);
            JamiumBot.log(userId.toString(), "Changed state to " + state, "");
        }
        else
        {
            JamiumBot.log(userId.toString(), "Was not found!", "");
        }

    }
}
