import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        // TODO Initialize Api Context
        ApiContextInitializer.init();

        // TODO Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // TODO Register our bot
        try {
//            botsApi.registerBot(new JamiumBot());
            // get DB info about users states
            Seeker.readFile();
            Seeker.parseFileInfo();
            botsApi.registerBot((LongPollingBot) new VerbsBot());
//            botsApi.registerBot((LongPollingBot) new Notificator());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
