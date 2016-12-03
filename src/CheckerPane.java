/**
 * Created by jonathanrach on 11/30/16.
 */

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CheckerPane extends GridPane
{

    private TextField[][] checkers = new TextField[7][8];

    /**
     * Create a CheckerPane of the passed Row Size and Column Size
     * @param rowSize The number of rows required to
     * @param columnSize
     */
    public CheckerPane(int rowSize, int columnSize)
    {
        setHgap(10);
        setVgap(10);

        for (int i=0; i<rowSize; i++)
        {
            for (int j=0; j<columnSize; j++)
            {
                int row = i;
                int column = j;

                TextField checker = new TextField("_");
                checker.setDisable(true);
                checker.setPrefWidth(25);

                checkers[row][column] = checker;
                add(checker,column,row);
            }
        }
    }

    /**
     * Method to add the label of "X" if player 1 selects a column and the label of "O" if player 2 selects a column
     * @param row The row to add the marker to
     * @param column The column to add the marker to
     * @param playerNumber The player adding the marker
     *
     * Credit to http://stackoverflow.com/questions/27154996/replace-a-node-at-row-col-in-a-javafx-gridpane for
     * assistance updating the proper node based on the row and column 
     *
     */
    public void addCheckerToPane(int row, int column, int playerNumber)
    {
        for (Node node : getChildren())
        {
            if ((node instanceof TextField) && (getColumnIndex(node) == column) && (getRowIndex(node) == row))
            {
                ((TextField) node).setText(playerNumber%10==1 ? "X":"O");
            }
        }
    }


}
