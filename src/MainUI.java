/**
 * Created by jonathanrach on 11/25/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.IllegalFormatException;

public class MainUI extends Application
{
    GameRules connect4Game = new GameRules();

    private BorderPane c4Pane = new BorderPane();
    private GridPane checkerPane = new GridPane();

    private Circle[][] checkers = new Circle[7][8];


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializec4Pane();

        Scene scene = new Scene(c4Pane, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializec4Pane()
    {
        System.out.println("c4Pane initialized");
        HBox genericInfoBox = new HBox();
        VBox playerInfoHolder = new VBox();

        initializeCheckerPane();

//        TODO: Add Current Player info, timer info
        genericInfoBox.getChildren().addAll(new Label ("Stuff 1 and Stuff 2"));//currentPlayerDisplay,timerDisplay);
        playerInfoHolder.getChildren().addAll(new Label("Hello"));

//        c4Pane.getChildren().addAll(genericInfoBox, playerInfoHolder, checkerPane);

        c4Pane.setTop(genericInfoBox);
        c4Pane.setLeft(playerInfoHolder);
        c4Pane.setCenter(checkerPane);
    }

    private void initializeCheckerPane()
    {
        checkerPane.setHgap(10);
        checkerPane.setVgap(10);
        System.out.println("checkerPane initialized");

        for (int i=0; i<7; i++)
        {
            for (int j=0; j<8; j++)
            {
                int row = i;
                int column = j;

                Circle circle = new Circle();
                circle.setRadius(30);
                circle.setFill(Color.BLUE);

                circle.setOnMouseClicked(e-> handleColumnSelected(column));
                checkers[i][j] = circle;
                checkerPane.add(circle,column,row);
            }
        }
    }

    private void handleColumnSelected(int column)
    {
        try
        {
            int rowToChangeColor = connect4Game.addChecker(column);
            updateCheckerColor(connect4Game.getPlayerNumber()==1 ? Color.RED:Color.BLACK, rowToChangeColor, column);
        }
        catch (IndexOutOfBoundsException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void updateCheckerColor(Color playerColor, int row, int column)
    {
        int uiRow = 6-row;
        if (uiRow>-1 && uiRow<7)
        {
            checkers[uiRow][column].setFill(playerColor);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error: Column is full!");
            connect4Game.decrementTurnNumber();
        }
    }

}
