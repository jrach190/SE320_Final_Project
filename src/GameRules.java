/**
 * Created by jonathanrach on 11/27/16.
 *
 * Gameboard Rules:
 * If gameboard[i][j] = 0, it is empty
 * If gameboard[i][j] = 1, it is filled by player 1's checker
 * If gameboard[i][j] = 2, it is filled by player 2's checker
 *
 */
public class GameRules {

    private int[][] gameboard = new int[7][8];

    private int turnNumber = 0;

    private boolean gameWon = false;

    public GameRules()
    {
        initializeBoard();
    }

    /**
     * Method to add a player number to the gameboard array to mark a spot as used by a player
     * @param column to add a checker to
     * @return the row the column was added to
     */
    public int addChecker(int column)
    {
        for (int i=0; i<7; i++)
        {
            if (gameboard[i][column] == 0)
            {
                gameboard[i][column] = getPlayerNumber();
                checkForWin();
                turnNumber++;
                System.out.print(getPlayerNumber());
                return i;
            }
        }
        throw new IndexOutOfBoundsException("Game Column Already Full");
    }

    /**
     * Method to return the private turnNumber variable for UI to determine checker color
     * @return the int value representing which player currently has move rights
     */
    public int getPlayerNumber()
    {
        return (turnNumber%2)+1;
    }

    /**
     * Method to decrement turnNumber if a UI exception occurs and player rollback must occur
     */
    public void decrementTurnNumber()
    {
        turnNumber--;
    }

    /**
     * Method to initialize all elements of the board array to 0
     */
    private void initializeBoard()
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<8; j++)
            {
                gameboard[i][j] = 0;
            }
        }
    }

    private boolean checkForWin()
    {

        return gameWon;
    }

    private boolean checkForVerticalWin()
    {
        return false;
    }

    private boolean checkForHorizontalWin()
    {
        return false;
    }

    private boolean checkForDiagonalWin()
    {
        return false;
    }

    public boolean getGameWonStatus()
    {
        return gameWon;
    }
}
