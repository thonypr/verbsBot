import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VerbsBot extends TelegramLongPollingBot {


    public static void log(String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            Long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {

                //show welcome screen
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                String userName = update.getMessage().getFrom().getFirstName();
                message.setText(Responses.WELCOME.replace("X, enter word in infinitive to get it forms", userName));
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage msg = new SendMessage();
                String verbToSearchFor = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                List<Verb> formsByVerb = Seeker.findFormsByVerb(verbToSearchFor);
                StringBuilder results = new StringBuilder();
                try {
                    for (Verb verb : formsByVerb) {
                        results.append("Infinitive: ").append(verb.getInfinitive()).append("\n");
                        results.append("PastSimple: ").append(verb.getPastSimple()).append("\n");
                        results.append("PastParticiple: ").append(verb.getPastParticiple()).append("\n");
                        results.append("Перевод: ").append(verb.getTranslation()).append("\n");
                    }
                } catch (Exception e) {
                    results.append("WTF?");
                }

                msg.setChatId(chatId);
                msg.setText(String.valueOf(results));
                try {
                    execute(msg); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getBotUsername() {
        return System.getenv("TG_VERBS_BOT_NAME");
    }

    public String getBotToken() {
        return System.getenv("TG_VERBS_BOT_ID");
    }
}
