package edu.io.net;

import edu.io.net.command.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Connect to server
        var connector = new GameServerConnector("tcp://localhost:1313", new SocketConnector());
        connector.connect();

        if (!connector.isConnected()) {
            System.out.println("Cannot connect to server");
            return;
        }

        System.out.println("Connected to server");

        System.out.print("Your name: ");
        String name = scanner.nextLine().trim();

        connector.issueCommand(new Handshake.Cmd("1.1.17"), response -> {
            System.out.println("Handshake: " + response);
        });
        Thread.sleep(300);

        connector.issueCommand(new JoinGame.Cmd(name), response -> {
            System.out.println("Join game: " + response);
        });
        Thread.sleep(300);

        connector.issueCommand(new GetInfo.Cmd(), response -> {
            System.out.println("Server info: " + response);
        });

        connector.onCmdFromServer(cmd -> {
            if (cmd instanceof UpdateState.Cmd) {
                System.out.println("Game update received");
            }
        });

        System.out.println("\nGame started");
        System.out.println("Type 'exit' to leave");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
        }

        connector.issueCommand(new LeaveGame.Cmd(), response -> {
            System.out.println("Left game: " + response);
        });

        connector.disconnect();
        System.out.println("Disconnected");
    }
}