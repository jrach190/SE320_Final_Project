import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by jonathanrach on 12/6/16.
 */
public class Launcher extends Application
{

    UIController uiController = new UIController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        displayWelcome();

        uiController.start(primaryStage);
    }

    public static void main (String[] args)
    {
        launch(args);
    }

    private void displayWelcome()
    {
        ButtonType internetButton = new ButtonType("Internet Game");
        ButtonType localButton = new ButtonType("Local Game");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Welcome to Connect 4!");
        alert.setContentText("Would you like this to be a local game or an internet game?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(internetButton,localButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == internetButton)
        {
            displayInternetSetUpDialog();
        }

    }

    private void displayInternetSetUpDialog()
    {
        ButtonType hostButton = new ButtonType("Host");
        ButtonType clientButton = new ButtonType("Connect");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Welcome to Connect 4!");
        alert.setContentText("Would you like to host the game or connect to another game?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(hostButton,clientButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()==hostButton)
        {
            Connection connection = new Connection(true);
            uiController.start(new Stage());
        }
        else if (result.get() == clientButton)
        {
            TextInputDialog addressDialog = new TextInputDialog("localhost");
            TextInputDialog hostDialog = new TextInputDialog("11000");

            addressDialog.setContentText("Enter the address of the host you would like to connect to:");
            Optional<String> hostAddress = addressDialog.showAndWait();
            Optional<String> portNumber = hostDialog.showAndWait();

            Connection connection = new Connection(false);
            connection.setHOSTIP(hostAddress.get());
        }
    }
}
