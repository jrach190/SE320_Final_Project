/**
 * Created by jonathanrach on 11/30/16.
 */

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CheckerPane extends GridPane
{
    private int rowSize;
    private int columnSize;

    private TextField[][] checkers;
    private Button[] selectionButtons;

    /**
     * Create a CheckerPane of the passed Row Size and Column Size
     * @param rowSize The number of rows required to
     * @param columnSize
     */
    public CheckerPane(int rowSize, int columnSize)
    {
        this.rowSize = rowSize;
        this.columnSize = columnSize;

        checkers = new TextField[rowSize][columnSize];
        selectionButtons = new Button[columnSize];

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

                checkers[row][column] = checker;
                add(checker,column,row);

                if (row == rowSize-1)
                {
                    Button selectionButton = new Button("Select");
                    selectionButtons[column] = selectionButton;
                    add(selectionButton,column,row+1);
                }
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
        int rowReversedForBoard = rowSize-row-1;
        for (Node node : getChildren())
        {
            if ((node instanceof TextField) && (getColumnIndex(node) == column) && (getRowIndex(node) == rowReversedForBoard))
            {
                ((TextField) node).setText(playerNumber%10==1 ? "X":"O");
            }
        }
    }

    /**
     * Return the button array to allow for actions to be set in UIController class
     * @return
     */
    public Button[] getSelectionButtons()
    {
        return selectionButtons;
    }


}
