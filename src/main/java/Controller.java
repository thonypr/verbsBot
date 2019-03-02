import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Controller {

    public void processCallback(String callback) {

    }

    public void processMessage(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            // find a response

        }
        else if (update.hasCallbackQuery()) {
            // Set variables

            long chat_id = update.getCallbackQuery().getMessage().getChatId();

                String answer = "Updated message text " + chat_id;

//                try {
//                    execute(new_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
            }
            // process callback from inline button
            Long chatId = update.getMessage().getChatId();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            String callback = update.getCallbackQuery().getData();
            switch(callback) {
                case "w_hi" : {
                    String answer = "HI!";
                    EditMessageText newMessage = new EditMessageText()
                            .setChatId(chatId)
                            .setMessageId(Integer.valueOf(String.valueOf(messageId)))
                            .setText(answer);
            }
        }
    }

    public void sendResponseToCallback(String answer) {

    }
}
