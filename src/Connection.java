import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.xml.crypto.Data;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jonathanrach on 12/6/16.
 *
 * Credit to Krishan Patel for assistance in the connection and communication logic
 */
public class Connection
{
    private String HOSTIP = "";
    private int PORT = 11000;

    private  ServerSocket serverSocket;
    private  Socket socket;

    private int columnToUpdate;

    public Connection(boolean isHost)
    {
        if (isHost)
        {
            establishHostConnection();
        }
        else
        {
            establishClientConnection();
        }
    }

    protected int getColumnToUpdate()
    {
        try
        {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            UIController.enableBoard();
            columnToUpdate = in.readInt();

            UIController.addCheckerToBoard(columnToUpdate);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        return columnToUpdate;
    }

    protected void sendColumnToUpdate(int column)
    {
        try
        {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            UIController.disableBoard();
            out.write(column);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    private void establishHostConnection()
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();

            System.out.println("Connected to client...");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void establishClientConnection()
    {
        try
        {
            socket = new Socket(HOSTIP, PORT);
        }
        catch (IOException e)
        {
            System.out.println("Host could not be found.");
            System.exit(0);
        }

        System.out.println("Connected to host...");
    }

    protected void closeConnection()
    {
        try
        {
            serverSocket.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setHOSTIP(String hostip)
    {
        this.HOSTIP = hostip;
    }

    public void setPORT(String port)
    {

    }
}
