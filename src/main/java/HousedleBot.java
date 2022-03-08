import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HousedleBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "HousedleBot";
    }

    public String getBotToken() {
        return "5266447317:AAEWHy_CZvSBF7W7gxhW_FjsO3yzk_R0ofw";
    }

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();

        if(command.equals("/start")) {
            System.out.println("Running start");
            introduction(update);
        } else if (command.equals("/register")) {
            System.out.println("Create account");
            createAccount(update);
        } else if (command.equals(("/play"))) {
            System.out.println("play");
            executePuzzle(update);

        } else if (command.equals(("/view"))) {
            viewAllPuzzles(update);
        } else if ((command.equals(("/learn")))) {
            System.out.println("/learn");
            learn(update);
        } else {
            introduction(update);
        }


    }

    private void viewAllPuzzles(Update update) {
        //TODO
    }

    private void learn(Update update) {
        //TODO
    }

    private void executePuzzle(Update update) {
        //TODO
    }

    private void createAccount(Update update) {
        //TODO
    }

    private void introduction(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());

        String message = "Welcome to Housedle.\n" +
                "This Covid Pandemic has caused the people worldwide to lose the opportunity" +
                "to have decent buildings to live in.\n" +
                "Volunteers cant enter villages directly to build homes and many make-shift solutions" +
                "are short-termed.\n\n\n" +
                "Lets play and learn more about the situation worldwide!\n" +
                "To Sign up /register \n" +
                "To Play /play \n" +
                "To View All Puzzles /view \n" +
                "To Learn more about the housing situation /learn";
        response.setText(message);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
