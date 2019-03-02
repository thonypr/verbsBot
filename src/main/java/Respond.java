import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Respond {

    private static ArrayList<InlineKeyboardButton> welcome = new ArrayList<>(
            Arrays.asList(
                    new InlineKeyboardButton().setText("Hello there!").setCallbackData("w_hi"),
                    new InlineKeyboardButton().setText("Tasks!").setCallbackData("w_tasks")
            ));

    public static List<InlineKeyboardButton> getWelcomeResponse() {
        return welcome;
    }
}