import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
                if(chat_id == 235486635) {
                    log(String.valueOf(chat_id), "admin!", "");
//                    SendPhoto photo = new SendPhoto();
                    SendMessage msg = new SendMessage()
                            .setText("admin!")
//                            .setReplyMarkup(InlineKeyboardResponses.getMediaKeyboard())
                            .setChatId(chat_id);
//                    photo.setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC");
                    try {
                        execute(msg); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
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
            }

            else if (UsersController.hasUser(chat_id)) {
                // check current state of user
                //response should be 2
                Long chatId = update.getMessage().getChatId();
                State userState = UsersController.getUser(chatId).getUserState();
                String response = "";
                SendMessage message = new SendMessage();
                switch (userState) {
                    case VIEW_TASK_1: {
                        response = Validator.task1(update.getMessage().getText());
                        if (response.equals(Responses.CONGRAT_1)) {
                            UsersController.updateUserState(chatId, State.SOLVED_TASK_1);
                            message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
                            //TODO: add notification to admin
                        } else {
                            message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
                            response = response + "\n" + Responses.TASK_1;
                        }
                        break;
                    }
                    case VIEW_TASK_2: {
                        response = Validator.task2(update.getMessage().getText());
                        if (response.equals(Responses.CONGRAT_2)) {
                            UsersController.updateUserState(chatId, State.SOLVED_TASK_2);
                            message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
                            //TODO: add notification to admin
                        } else {
                            message.setReplyMarkup(InlineKeyboardResponses.getTasksKeyboard());
                            response = response + "\n" + Responses.TASK_2;
                        }
                        break;
                    }
                }

                message.setChatId(chatId);
                message.setText(response);

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            log(String.valueOf(update.getMessage().getChatId()), "photo!", "");
            // Message contains photo
            // Set variables
            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            log(String.valueOf(update.getMessage().getChatId()), "fid = " + f_id, "");
            SendPhoto msg = new SendPhoto()
                    .setChatId(update.getMessage().getChatId())
                    .setPhoto(f_id)
                    .setCaption(f_id);
            log(String.valueOf(update.getMessage().getChatId()), "sent!", "");
            try {
                execute(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {
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
                        .setText(Responses.TASK_1);
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
                SendPhoto message = new SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto("AgADAgADYaoxGyIE4EuR-IaPxtflYsxCXw8ABAnMjSHpJp8QBE8CAAEC")
                        .setCaption(Responses.TASK_2);
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
