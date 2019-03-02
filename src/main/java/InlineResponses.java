import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineResponses {

    private static ArrayList<InlineKeyboardButton> tasks = new ArrayList<>(
            Arrays.asList(
                    new InlineKeyboardButton().setText("Task 1").setCallbackData("t_1"),
                    new InlineKeyboardButton().setText("Task 2").setCallbackData("t_2")
            ));

    public static List<InlineKeyboardButton> getTasksInlineButtons() {
        return tasks;
    }
}