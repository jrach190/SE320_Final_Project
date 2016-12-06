/**
 * Created by jonathanrach on 12/5/16.
 */

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Connect4InfoButtonBox extends VBox {

    private Button[] buttons = new Button[2];

    public Connect4InfoButtonBox()
    {
        setStyle("-fx-border-color: dimgrey");

        TextInputDialog player1Name = new TextInputDialog("Player 1");
        TextInputDialog player2Name = new TextInputDialog("Player 2");

        player1Name.setHeaderText("Player 1");
        player1Name.setContentText("Enter the name of the first player:");
        player2Name.setHeaderText("Player 2");
        player2Name.setContentText("Enter the name of the second player:");

        Label player1Label = new Label("Player 1 (O): " + player1Name.showAndWait().get());
        Label player2Label = new Label("Player 2 (X): " + player2Name.showAndWait().get());

        Button restartButton = new Button("Restart");
        Button forfeitButton = new Button("Forfeit");

        buttons[0]=restartButton;
        buttons[1]=forfeitButton;

        getChildren().addAll(player1Label,player2Label,restartButton,forfeitButton);

    }

    public Button[] getButtons()
    {
        return buttons;
    }
}
