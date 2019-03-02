import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JamiumBot extends TelegramLongPollingBot {

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
            long chat_id = update.getMessage().getChatId();
            //process user
            UsersController.addUser(chat_id);
            if (message_text.equals("/start")) {
                //show welcome screen
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                String userName = update.getMessage().getFrom().getUserName();
                message.setText(Responses.WELCOME.replace("X", userName));
                message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
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
            SendMessage new_message = new SendMessage();
            if (call_data.equals("t_1")) {
                //process user
                UsersController.updateUserState(chat_id, State.VIEW_TASK_1);
                String answer = "I'm task 1 " + chat_id;
                new_message = new SendMessage()
                        .setChatId(chat_id)
//                        .setMessageId(Integer.valueOf(String.valueOf(message_id)))
                        .setText(answer);
            }
            else if (call_data.equals("t_2")) {
                UsersController.updateUserState(chat_id, State.VIEW_TASK_2);
                String answer = "And I'm task 2! " + chat_id;
                new_message = new SendMessage()
                        .setChatId(chat_id)
//                        .setMessageId(Integer.valueOf(String.valueOf(message_id)))
                        .setText(answer);
            }
                try {
                    execute(new_message);
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
