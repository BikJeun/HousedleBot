import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Program is running");
            //Instantiate TeleBot API
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            //Register the bot
            telegramBotsApi.registerBot(new HousedleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
