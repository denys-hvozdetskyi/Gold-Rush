package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.placeToken(3, 2, new GoldToken());
        board.placeToken(6, 1, new GoldToken());

        PlayerToken player = new PlayerToken(board, 0, 0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();
            System.out.println("Move (w/a/s/d): ");
            String input = scanner.nextLine();

            try {
                switch (input) {
                    case "a" -> player.move(PlayerToken.Move.LEFT);
                    case "d" -> player.move(PlayerToken.Move.RIGHT);
                    case "w" -> player.move(PlayerToken.Move.UP);
                    case "s" -> player.move(PlayerToken.Move.DOWN);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
