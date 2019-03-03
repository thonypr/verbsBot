import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Notificator {

    public static SendMessage sendToAdmin(String text) {
        long chatId = 235486635;
        SendMessage msg = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        return msg;
    }
}
