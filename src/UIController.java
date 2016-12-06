/**
 * Created by jonathanrach on 12/1/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Optional;

public class UIController extends Application {

    private GameRules gameRules = new GameRules();
    private BorderPane c4Pane = new BorderPane();
    private CheckerPane boardDisplay;
    private Connect4InfoButtonBox buttonAndInfoBox;
    private PlayerAndTimerDisplayBar turnAndTimerBar;

    private static int ROWSIZE = 7;
    private static int COLUMNSIZE = 8;

    private boolean timerStarted = false;

    private Timer timer = new Timer();

    public static void main (String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) {
        initializec4Pane();


        Scene scene = new Scene(c4Pane, 660, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method to initialize the entire display
     */
    private void initializec4Pane()
    {

        boardDisplay = new CheckerPane(ROWSIZE, COLUMNSIZE);
        buttonAndInfoBox = new Connect4InfoButtonBox();
        turnAndTimerBar = new PlayerAndTimerDisplayBar();

        System.out.println("c4Pane initialized");

        initializeBoardButtons();
        initializeButtonBoxButtons();
        initializeTurnAndTimerBar();

        c4Pane.setTop(turnAndTimerBar);
        c4Pane.setLeft(buttonAndInfoBox);
        c4Pane.setCenter(boardDisplay);
    }

    /**
     * Method to initialize the TurnAndTimerBar to display the proper play whose turn it is
     */
    private void initializeTurnAndTimerBar()
    {
        turnAndTimerBar.setTurnLabel("Player " + gameRules.getPlayerNumber());
    }

    /**
     * Method to initialize the buttons from the boardDisplay and add their event listeners
     */
    private void initializeBoardButtons()
    {
        Button[] buttons = boardDisplay.getSelectionButtons();

        for (int i=0; i<buttons.length; i++)
        {
            int column = i;
            buttons[i].setOnAction(e->
            {
                try
                {
                    boardDisplay.addCheckerToPane(gameRules.addChecker(column), column, gameRules.getPlayerNumber());
                    turnAndTimerBar.setTurnLabel("Player " + gameRules.getPlayerNumber());

                    if (!timerStarted)
                    {
                        timer.start();
                        timerStarted=true;
                        timer.switchPlayerCounter(gameRules.getPlayerNumber());
                    }

                    if (gameRules.getGameWonStatus() && gameRules.getWinner()!= 0)
                    {
                        displayGameOverDialog();
                    }
                }
                catch (IndexOutOfBoundsException outOfBoundsException)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(outOfBoundsException.getMessage());
                    alert.showAndWait();
                }
            });
        }
    }

    /**
     * Method to initialize the buttons from the buttonAndInfoBox and add their event listeners
     */
    private void initializeButtonBoxButtons()
    {
        Button[] buttons = buttonAndInfoBox.getButtons();

        for (Button button : buttons)
        {
            if (button.getText() == "Restart")
            {
                button.setOnAction(e->resetGame());
            }
            if (button.getText() == "Forfeit")
            {
                button.setOnAction(e->
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to forfeit the game?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        gameRules.setWinner((gameRules.getPlayerNumber()+1)%2 +1);
                        displayGameOverDialog();
                    }
                });
            }
        }
    }

    /**
     * Method to display the dialog when a game is over.
     */
    private void displayGameOverDialog() {
        ButtonType restartButton = new ButtonType("New Game");
        ButtonType exitButton = new ButtonType("Exit Game");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (gameRules.getGameWonStatus())
            alert.setContentText("Player " + gameRules.getWinner() + " wins!");
        else if (gameRules.getGameTiedStatus())
            alert.setContentText("Game over! The game was tied. Nobody wins...");
        alert.getButtonTypes().setAll(restartButton,exitButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == restartButton)
        {
            resetGame();
        }
        if (result.get() == exitButton)
        {
            System.exit(1);
        }
    }

    /**
     * Method to reset the UI board and the board on the backend
     */
    private void resetGame() {
        boardDisplay.resetCheckerPane();
        gameRules.resetBoard();
    }
}
