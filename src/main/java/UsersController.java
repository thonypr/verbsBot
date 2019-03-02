import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersController {

    private static HashMap<Long, User> users = new HashMap<>();
    private static List<Long> usersIds = new ArrayList<>();

    private boolean hasUser(User user) {
        return usersIds.contains(user.getUserId());
    }

    public static void addUser(Long userId) {
        if(users.containsKey(userId)) {
            // we have him here
            JamiumBot.log(userId.toString(), "We got him!", "");
        }
        else {
            User newUser = new User(userId, State.WELCOME);
            users.put(userId, newUser);
            usersIds.add(userId);
            JamiumBot.log(userId.toString(), "Collected!", "");
        }
    }

    public static String getUsersIds() {
        return usersIds.toString();
    }

    public static void updateUserState(Long userId, State state) {
        if(users.containsKey(userId)) {
            User newUser = new User(userId, state);
            users.replace(userId, newUser);
        }
        else
        {
            JamiumBot.log(userId.toString(), "Was not found!", "");
        }

    }
}
