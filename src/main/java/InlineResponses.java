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

    private static ArrayList<InlineKeyboardButton> task1 = new ArrayList<>(
            Arrays.asList(
                    new InlineKeyboardButton().setText("Task 1").setCallbackData("t_1")
            ));

    private static ArrayList<InlineKeyboardButton> task2 = new ArrayList<>(
            Arrays.asList(
                    new InlineKeyboardButton().setText("Task 2").setCallbackData("t_2")
            ));

    private static ArrayList<InlineKeyboardButton> attempt1 = new ArrayList<>(
            Arrays.asList(
                    new InlineKeyboardButton().setText("Ну как, есть идеи?").setCallbackData("at_1")
            ));

    public static List<InlineKeyboardButton> getTasksInlineButtons() {
        return tasks;
    }

    public static List<InlineKeyboardButton> getAttempt1InlineButton() {
        return attempt1;
    }

    public static List<InlineKeyboardButton> getTask1InlineButtons() {
        return task1;
    }

    public static List<InlineKeyboardButton> getTask2InlineButtons() {
        return task2;
    }
}