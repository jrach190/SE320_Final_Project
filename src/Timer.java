/**
 * Created by jonathanrach on 12/6/16.
 *
 * Based off code from http://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html
 */
public class Timer extends Thread
{
    private long start;
    private long totalElapsedTime;
    private long totalElapsedForPlayer1;
    private long totalElapsedForPlayer2;

    public Timer()
    {
        start = System.currentTimeMillis();
    }

    public double getTimeElapsedFromThreadCreation()
    {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public void run()
    {

    }

    public void switchPlayerCounter(int playerNumber)
    {

    }


}
