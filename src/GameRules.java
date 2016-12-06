import java.util.Random;

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

    Random random = new Random();

    private int[][] gameboard = new int[7][8];

    private int turnNumber = random.nextInt(1);
    private int winner;

    private boolean gameWon = false;
    private boolean isTied = false;


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
                if (!gameWon) {
                    gameboard[i][column] = getPlayerNumber();
                }
                checkForWin();
                checkForTie();
                turnNumber++;
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
     * Method to return the int value of the winner
     * @return The winner of the game
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Method to set the winner in the case of a forfeit game
     * @param winner the int value of the player who won
     */
    public void setWinner(int winner)
    {
        this.winner = winner;
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
        if (checkForVerticalWin() || checkForHorizontalWin() || checkforDiagonalLeftWin() || checkForDiagonalRightWin())
        {
            gameWon = true;
        }
        return gameWon;
    }

    private boolean checkForTie()
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<8; j++)
            {
                if (gameboard[i][j] == 0)
                {
                    isTied = false;
                    return isTied;
                }
            }
        }
        isTied=true;
        return isTied;
    }

    private boolean checkForVerticalWin()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<8; j++)
            {
                if ((gameboard[i][j] == gameboard[i+1][j]) && (gameboard[i][j] == gameboard[i+2][j]) &&
                        (gameboard[i][j] == gameboard[i+3][j]) && (gameboard[i][j] != 0))
                {
                    System.out.println("Player has won vertically on backend");
                    winner = gameboard[i][j];
                    return true;
                }
            }

        }
        return false;
    }

    private boolean checkForHorizontalWin()
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<5; j++)
            {
                if ((gameboard[i][j] == gameboard[i][j+1]) && (gameboard[i][j] == gameboard[i][j+2]) &&
                        (gameboard[i][j] == gameboard[i][j+3]) && (gameboard[i][j] != 0))
                {
                    System.out.println("Player has won horizontally on backend");
                    winner = gameboard[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkForDiagonalRightWin() {
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<5; j++)
            {
                if ((gameboard[i][j] == gameboard[i+1][j+1]) && (gameboard[i][j] == gameboard[i+2][j+2]) &&
                (gameboard[i][j] == gameboard[i+3][j+3]) && gameboard[i][j] != 0)
                {
                    System.out.println("Player has won diagonally to the right on backend");
                    winner = gameboard[i][j];
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkforDiagonalLeftWin()
    {
        for (int i=0; i<4; i++)
        {
            for (int j=3; j<8; j++)
            {
                if ((gameboard[i][j] == gameboard[i+1][j-1]) && (gameboard[i][j] == gameboard[i+2][j-2]) &&
                        (gameboard[i][j] == gameboard[i+3][j-3]) && gameboard[i][j] != 0)
                {
                    System.out.println("Player has won diagonally to the left on backend");
                    winner = gameboard[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getGameWonStatus()
    {
        return gameWon;
    }

    public boolean getGameTiedStatus()
    {
        return isTied;
    }

    public void resetBoard()
    {
        initializeBoard();
        gameWon = false;
        isTied = false;
    }
}
