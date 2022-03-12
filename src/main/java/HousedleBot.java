import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.SendResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendGame;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
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

        if(command.equals("/start") ||command.equals("/play") ||
        command.equals("/view") ||command.equals("/volunteer") ||command.equals("/learn") ||command.equals("/donate")) {
                        currentState = null;
            }

        if(update.hasCallbackQuery()) {
            System.out.println(update.getCallbackQuery().getMessage().getFrom().getUserName());
            AnswerCallbackQuery query = new AnswerCallbackQuery();
            query.setCallbackQueryId(update.getCallbackQuery().getId());
            query.setUrl("http://localhost:63342/Housedle/housedle-puzzle/puzzle.html?_ijt=88ijtdm21l5prmdkt7sbtvtone#");


            try {
                execute(query);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


            if(command.equals("/start") || currentState == StateEnum.INTRO) {
                System.out.println("Running start");
                introduction(update);
            } else if (command.equals("/play")|| currentState == StateEnum.PLAY || update.hasCallbackQuery()) {
                System.out.println("play");
                executePuzzle(update);
            } else if (command.equals("/volunteer")) {
                System.out.println("/volunteer");
                volunteer(update);
            } else if (command.equals("/learn")) {
                System.out.println("/learn");
                learn(update);
            } else if (command.equals(("/donate"))) {
                System.out.println("/donate");
                donate(update);
            } else {
                introduction(update);
            }

        }

        private void donate(Update update) {
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            String message = null;
            message = "Every donation has a big impact. Thanks to you, Habitat can continue transforming lives in Singapore and around the world. Donate now: https://bit.ly/3vZ5qlu";
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


        private void volunteer(Update update) {
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            String message = null;
            message = "Change starts with you, and the people who want to support you. Together, we have the ability to effect great change in the lives of families in need of decent housing. Be a volunteer now: https://bit.ly/3pZt0ea";
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void learn(Update update) {
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            String message = null;
            message = "Thank you for your interest in knowing more about Habitat for Humanity, and what we do! Since 2004, we have been actively engaging volunteers to work alongside us in fighting poverty housing in Singapore and across the Asia-Pacific region. You can find out more about us here: https://bit.ly/3tTvZ8T";
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void executePuzzle(Update update) {
            if(currentState != StateEnum.PLAY) {
                currentState = StateEnum.PLAY;
                System.out.println("play game");
                try {
                    execute(new SendGame(update.getMessage().getChatId().toString(), "housedle"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(update.getCallbackQuery().getMessage().getFrom().getUserName());
                AnswerCallbackQuery query = new AnswerCallbackQuery();
                query.setCallbackQueryId(update.getCallbackQuery().getId());
                query.setUrl("http://localhost:63342/Housedle/housedle-puzzle/puzzle.html?_ijt=88ijtdm21l5prmdkt7sbtvtone#");


                try {
                    execute(query);
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

                message = "Welcome to Housedle.\n\n" +
                        "Did you know that nearly 1.6 billion people around the world do not have a place that they can call home? Along with the pandemic, this has caused more people to lose the opportunity to live in decent buildings.\n\n" +
                        "Join us as we explore how we can do our part to create decent home environments for everyone, both within and beyond the home.\n\n" +
                        "To join our daily challenges /play \n" +
                        "Be a volunteer /volunteer \n" +
                        "Make a donation /donate \n" +
                        "Find out more about Habitat for Humanity /learn \n";
            } else {
                message = "Explore Housedle by executing any of the following commands!\n\n" +
                        "To join our daily challenges /play \n" +
                        "Be a volunteer /volunteer \n" +
                        "Make a donation /donate \n" +
                        "Find out more about Habitat for Humanity /learn \n";
            }
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }