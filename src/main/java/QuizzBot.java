/**
 * Created by drmax on 9/26/17.
 */
// importing bot libraries
import com.mongodb.*;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//importing Mongo DB libraries


public class QuizzBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        //connecting to Mongo
        MongoClientURI uri = new MongoClientURI(
                "mongodb://test:testingTheDB01@quizbot-shard-00-00-bi5vw.mongodb.net:27017,quizbot-shard-00-01-bi5vw.mongodb.net:27017,quizbot-shard-00-02-bi5vw.mongodb.net:27017/test?ssl=true&replicaSet=quizBot-shard-0&authSource=admin");
        //testingTheDB01
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "QuizzBot_bot";
    }

    @Override
    public String getBotToken() {
        return "423712729:AAHRy3okTBfx0BfxTX5E7hsrcYyXzyEZpn4";
    }
}
