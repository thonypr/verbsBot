import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
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

            if (message_text.equals("/start")) {
                //process user
                UsersController.addUser(chat_id);
                //show welcome screen
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                String userName = update.getMessage().getFrom().getFirstName();
                message.setText(Responses.WELCOME.replace("X", userName));
                message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
//                Keyboards.setWelcomeButtons(message);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (UsersController.hasUser(chat_id)) {
                // check current state of user
                //response should be 2
                if (UsersController.getUser(chat_id).getUserState() == State.VIEW_TASK_1) {
                    //TODO: check method
                    //show welcome screen
                    SendMessage message = new SendMessage();
                    String response = Validator.task1(message_text);
                    if (response.equals(Responses.CONGRAT_1)) {
                        UsersController.updateUserState(chat_id, State.SOLVED_TASK_1);
                        message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
                        //TODO: add notification to admin
                    }
                    message.setChatId(chat_id);
                    message.setText(response);

                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.equals("t_1")) {
                //process user
                UsersController.updateUserState(chat_id, State.VIEW_TASK_1);
                String answer = "I'm task 1 " + chat_id;
                AnswerCallbackQuery callBack = new AnswerCallbackQuery()
                        .setCallbackQueryId(update.getCallbackQuery().getId());
//                        .setMessageId(Integer.valueOf(String.valueOf(message_id)))
//                        .setText(answer);
                try {
                    execute(callBack);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                //and show Task 1
                SendMessage message = new SendMessage()
//                        .setReplyMarkup(InlineKeyboardResponses.getAttemptKeyboard(1))
                        .setChatId(chat_id)
                        .setText("Hi, Im task #1 in text");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (call_data.equals("t_2")) {
                UsersController.updateUserState(chat_id, State.VIEW_TASK_2);
                String answer = "And I'm task 2! " + chat_id;
                AnswerCallbackQuery callBack = new AnswerCallbackQuery()
                        .setCallbackQueryId(update.getCallbackQuery().getId());
//                        .setChatId(chat_id)
//                        .setMessageId(Integer.valueOf(String.valueOf(message_id)))
//                        .setText(answer);
                try {
                    execute(callBack);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                //and show Task 2
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Hi, Im task #2 in text");
                try {
                    execute(message);
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
