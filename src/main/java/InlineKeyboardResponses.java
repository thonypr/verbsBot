import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static InlineKeyboardMarkup getKeyboardFromTasks(List <TaskDB> tasks) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        // Collect options
        List<InlineKeyboardButton> options = new ArrayList<>();
        for(TaskDB task : tasks) {
            InlineKeyboardButton btn = new InlineKeyboardButton().setText(task.getName());
            options.add(btn);
        }
        rowsInline.add(options);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
