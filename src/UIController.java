import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by jonathanrach on 12/1/16.
 */
public class UIController extends Application {

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
        genericInfoBox.getChildren().addAll(new Label("Stuff 1 and Stuff 2"));//currentPlayerDisplay,timerDisplay);
        playerInfoHolder.getChildren().addAll(new Label("Hello"));


        c4Pane.setTop(genericInfoBox);
        c4Pane.setLeft(playerInfoHolder);
//        c4Pane.setCenter(checkerPane);
        c4Pane.setCenter(boardDisplay);
    }





}
