import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // TODO Initialize Api Context
        ApiContextInitializer.init();

        // TODO Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // TODO Register our bot
        try {
//            botsApi.registerBot(new JamiumBot());
            // get DB info about users states
            HashMap<Long, User> users = DBConnection.getUsers();
            UsersController.setUsers(users);
            botsApi.registerBot((LongPollingBot) new JamiumBot());
//            botsApi.registerBot((LongPollingBot) new Notificator());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
