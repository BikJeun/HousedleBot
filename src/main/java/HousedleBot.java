import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HousedleBot extends TelegramLongPollingBot {

    StateEnum currentState = null;



    public String getBotUsername() {
        return "HousedleBot";
    }

    public String getBotToken() {
        return "5266447317:AAEWHy_CZvSBF7W7gxhW_FjsO3yzk_R0ofw";
    }

    public void onRegister() {

    }

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();

        if(command.equals("/start") ||command.equals("/register") ||command.equals("/play") ||
                command.equals("/view") ||command.equals("/volunteer") ||command.equals("/donate")) {
            currentState = null;
        }


        if(command.equals("/start") || currentState == StateEnum.INTRO) {
            System.out.println("Running start");
            introduction(update);
        } else if (command.equals("/register") || currentState == StateEnum.CREATEACCOUNT) {
            System.out.println("Create account");
            createAccount(update);
        } else if (command.equals(("/play"))) {
            System.out.println("play");
            executePuzzle(update);

        } else if (command.equals(("/view"))) {
            viewAllPuzzles(update);
        } else if (command.equals(("/volunteer"))) {
            System.out.println("/volunteer");
            learn(update);
        } else if (command.equals(("/donate"))) {
            System.out.println("/donate");
            donate(update);
        } else {
            introduction(update);

        }


    }

    private void donate(Update update) {
        //TODO
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
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());


        if(currentState != StateEnum.CREATEACCOUNT) {    //first time enter
            currentState = StateEnum.CREATEACCOUNT;

            String message = "Welcome to Housedle.\n" +
                    "You do not exist in our system yet." +
                    "Lets create an account so you can track you progress!\n\n" +
                    "Create a username";
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            String message2 = "Ensure that you key in the desired username on the first try.\n" +
                    "Ensure that there are no spaces in the username.\n\n" +
                    "Thank you and welcome to Housedle!";
            response.setText(message2);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else { //reading the user input to save username
            System.out.println("Receiving user input");
            final String[] message = {null};
            final String username = update.getMessage().getText();

            UserEntity newAccount = new UserEntity(update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName(), username);

            String uid = update.getMessage().getFrom().getId().toString();
           /* FirebaseOptions options = null;
            try {
                options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .setDatabaseUrl("https://hackforgood.firebaseio.com/")
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FirebaseApp.initializeApp(options);*/


            //HELP ITS FAILING HERE!!
            // ERROR : java.lang.IllegalStateException: FirebaseApp with name [DEFAULT] doesn't exist.
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference(uid).child("Account");
            String key = ref.push().getKey();
            System.out.println("ok");
            Map<String, Object> childUpdate = new HashMap<String, Object>();
            childUpdate.put(key, newAccount.toFirebaseObject());
            ref.updateChildren(childUpdate, new DatabaseReference.CompletionListener() {
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        System.out.println("Saved in db");
                        message[0] = "Welcome " + username + "!\n" +
                                "Thank you for joining Housedle, lets go have fun!\n\n" +
                                "Participate in our daily puzzles and complete them to save them in your gallery.\n" +
                                "After each puzzle, you will get to find out more about the location as well.\n" +
                                "Do consider volunteering or donating to help this issue worldwide!";

                    } else {
                        message[0] = "There seems to be an error. Do contact the creators to report this error.\n" +
                                "Sorry for the inconvenience caused";
                    }
                }});
                    response.setText(message[0]);

                    try {
                        execute(response);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

        }


    private void introduction(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String message = null;
        if(currentState != StateEnum.INTRO) {
            currentState = StateEnum.INTRO;

            message = "Welcome to Housedle.\n" +
                    "This Covid Pandemic has caused the people worldwide to lose the opportunity" +
                    "to have decent buildings to live in.\n" +
                    "Volunteers cant enter villages directly to build homes and many make-shift solutions" +
                    "are short-termed.\n\n\n" +
                    "Lets play and learn more about the situation worldwide!\n" +
                    "To Sign up /register \n" +
                    "To Play /play \n" +
                    "To View All Puzzles /view \n" +
                    "To Learn more about the housing situation /learn";

        } else {
            message = "Explore Housedle by executing any of the following commands! :)\n\n" +
                    "To Sign up /register \n" +
                    "To Play /play \n" +
                    "To View All Puzzles /view \n" +
                    "To Learn more about the housing situation /learn";

        }
        response.setText(message);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
