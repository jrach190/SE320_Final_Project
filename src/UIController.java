/**
 * Created by jonathanrach on 12/1/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setContentText("Player " + gameRules.getWinner() + " wins!");
//                        alert.showAndWait();

                        ChoiceDialog choiceDialog = new ChoiceDialog();
                        choiceDialog.setContentText("Player " + gameRules.getWinner() + " wins! Select from the " +
                                "box what you want to do next...");
//                        choiceDialog.getItems().addAll()
                        choiceDialog.showAndWait();
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

        c4Pane.setTop(genericInfoBox);
        c4Pane.setLeft(playerInfoHolder);
        c4Pane.setCenter(boardDisplay);
    }





}
