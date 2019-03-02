import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton("Hey! I'm inline button!");
            button.setCallbackData("callback_data");
            rowInline.add(button);
            rowsInline.add(rowInline);
            List<List<InlineKeyboardButton>> buttons = new ArrayList<List<InlineKeyboardButton>>();
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
//            rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {
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
