import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardResponses {

    public static InlineKeyboardMarkup createSetOfOptions(List<InlineKeyboardButton> options) {
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

    public static InlineKeyboardMarkup getTasksKeyboard() {
        InlineKeyboardMarkup setOfOptions = createSetOfOptions(InlineResponses.getTasksInlineButtons());
        return setOfOptions;
    }

    public static InlineKeyboardMarkup getMediaKeyboard() {
        InlineKeyboardMarkup setOfOptions = createSetOfOptions(InlineResponses.getMediaButtons());
        return setOfOptions;
    }

    public static InlineKeyboardMarkup getShowUsersKeyboard() {
        InlineKeyboardMarkup setOfOptions = createSetOfOptions(InlineResponses.getAdminButtons());
        return setOfOptions;
    }

    public static InlineKeyboardMarkup getTaskKeyboard(int taskId) {
        switch (taskId) {
            case 1: return createSetOfOptions(InlineResponses.getTask1InlineButtons());
            case 2: return createSetOfOptions(InlineResponses.getTask2InlineButtons());
            default: return null;
        }
    }

    public static ReplyKeyboardMarkup getKeyboardFromTasks(List <TaskDB> tasks) {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowsInline = new ArrayList<>();
        // Collect options
        KeyboardRow options = new KeyboardRow();
        for(TaskDB task : tasks) {
            KeyboardButton btn = new KeyboardButton().setText(task.getName());
            options.add(btn);
        }
        rowsInline.add(options);
        // Add it to the message
        markup.setKeyboard(rowsInline);
        return markup;
    }
}
