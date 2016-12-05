/**
 * Created by jonathanrach on 12/1/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class UIController extends Application {


    private GameRules gameRules = new GameRules();
    private BorderPane c4Pane = new BorderPane();
    private CheckerPane boardDisplay;

    private static int ROWSIZE = 7;
    private static int COLUMNSIZE = 8;

    public static void main (String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) {
        initializec4Pane();

        Scene scene = new Scene(c4Pane, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializec4Pane()
    {

        boardDisplay = new CheckerPane(ROWSIZE, COLUMNSIZE);

        System.out.println("c4Pane initialized");
        HBox genericInfoBox = new HBox();
        VBox playerInfoHolder = new VBox();

//        TODO: Add Current Player info, timer info
//        genericInfoBox.getChildren().addAll(new Label("Stuff 1 and Stuff 2"));//currentPlayerDisplay,timerDisplay);
//        playerInfoHolder.getChildren().addAll(new Label("Hello"));

        initializeButtons();

        c4Pane.setTop(genericInfoBox);
        c4Pane.setLeft(playerInfoHolder);
        c4Pane.setCenter(boardDisplay);
    }

    private void initializeButtons()
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
                    if (gameRules.getGameWonStatus() && gameRules.getWinner()!= 0)
                    {
                        ButtonType restartButton = new ButtonType("New Game");
                        ButtonType exitButton = new ButtonType("Exit Game");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Player " + gameRules.getWinner() + " wins!");
                        alert.getButtonTypes().setAll(restartButton,exitButton);

                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.get() == restartButton)
                        {
                            //TODO Restart Game
                            boardDisplay.resetCheckerPane();
                            gameRules.resetBoard();
                        }
                        if (result.get() == exitButton)
                        {
                            System.exit(1);
                        }

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



}
