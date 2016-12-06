/**
 * Created by jonathanrach on 12/5/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    UIController uiController = new UIController(true);
    GameRules gameRules = new GameRules();

    public Server() {
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket client = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        ) {
            System.out.println("Server is listening");
            Integer input, output;
            Connection connection = new Connection();

            while ((input = in.read()) != null)
            {
                uiController.addCheckerToBoard(input);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }


}
