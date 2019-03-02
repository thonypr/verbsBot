import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class JamiumBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {
                //show welcome screen
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                String userName = update.getMessage().getFrom().getUserName();
                message.setText(Responses.WELCOME.replace("X", userName));

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("callback_data")) {
                String answer = "Updated message text " + chat_id;
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(Integer.valueOf(String.valueOf(message_id)))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getBotUsername() {
        return "testForQuizBot";
    }

    public String getBotToken() {
        return "705804073:AAGN3-TdtwVygVP02M0mct5l57GHSq07K_8";
    }
}
