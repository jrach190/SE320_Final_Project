/**
 * Created by jonathanrach on 12/5/16.
 */

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PlayerAndTimerDisplayBar extends HBox
{
    private Label playerDisplayLabel = new Label("Who's turn is it?");

    private Button playerTurnLabel;

    private Button timerDisplayLabel;

    public PlayerAndTimerDisplayBar()
    {

        setStyle("-fx-border-color: dimgrey");

        playerTurnLabel = new Button("Player  ");
        timerDisplayLabel = new Button("00:00");

        playerTurnLabel.setDisable(true);
        timerDisplayLabel.setDisable(true);

        getChildren().addAll(playerDisplayLabel,playerTurnLabel,timerDisplayLabel);
    }

    public void setTurnLabel(String labelText)
    {
        playerTurnLabel.setText(labelText);
    }

    public void updateTimerDisplay(String timerValue)
    {
        timerDisplayLabel.setText(timerValue);
    }

}
