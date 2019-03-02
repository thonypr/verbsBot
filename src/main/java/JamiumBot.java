import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class JamiumBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            //SendMessage message = new SendMessage() // Create a message object object
            //        .setChatId(chat_id)
            //        .setText(message_text);

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            message.setText(message_text);
            InlineKeyboardMarkup options = new InlineKeyboardMarkup();
            InlineKeyboardButton button = new InlineKeyboardButton("Hey! I'm inline button!");
            List<InlineKeyboardButton> b1 = new ArrayList<InlineKeyboardButton>();
            b1.add(button);
            List<List<InlineKeyboardButton>> buttons = new ArrayList<List<InlineKeyboardButton>>();
            buttons.add(b1);
            options.setKeyboard(buttons);
            message.setReplyMarkup(options);

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
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
