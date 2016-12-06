import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jonathanrach on 12/5/16.
 */
public class Client {

    public Client()
    {
        try {
            Socket s = new Socket("10.33.83.232", 11000);

            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            DataInputStream inputStream = new DataInputStream(in);
            DataOutputStream outputStream = new DataOutputStream(out);

            Scanner input = new Scanner(in);
            System.out.println(input.nextLine());
            in.close();
            out.close();
            inputStream.close();
            outputStream.close();
            s.close();
        }
        catch (IOException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
    }

    public static void main (String[] args)
    {
        new Client();
    }
}
