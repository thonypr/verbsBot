import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class WelcomeResponse implements BaseResponse {

    @Override
    public InlineKeyboardMarkup createSetOfOptions(List<InlineKeyboardButton> options) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        // Collect options
        rowsInline.add(options);
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public SendMessage addWelcomeMessage(SendMessage message) {
        InlineKeyboardMarkup setOfOptions = createSetOfOptions(Respond.getWelcomeResponse());
        message.setReplyMarkup(setOfOptions);
        return message;
    }
}
