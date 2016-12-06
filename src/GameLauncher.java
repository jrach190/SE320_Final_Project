import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by jonathanrach on 12/5/16.
 */
public class GameLauncher extends Application {

    private boolean isHost = true;
    private boolean isClient = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        performHostClientCheck();

        UIController gameController = new UIController();
        gameController.start(primaryStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void performHostClientCheck()
    {
        ButtonType clientButton = new ButtonType("Client");
        ButtonType hostButton = new ButtonType("Host");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();

        alert.getButtonTypes().addAll(clientButton,hostButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == clientButton)
        {
            Client client = new Client();
        }

        if (result.get() == hostButton)
        {
            Server server = new Server();
        }
    }
}
