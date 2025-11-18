package edu.io;

import edu.io.player.Player;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import edu.io.token.WaterToken;
import java.util.Scanner;

public class Game {
    private final Board board;
    private Player player;

    public Game() {
        this.board = new Board();
    }

    public Board board() {
        return this.board;
    }

    public void join(Player player) {
        this.player = player;

        player.vitals.setOnDeathHandler(() -> {
            System.out.println("To koniec: pełne odwodnienie. Gracz jest niedysponowany i nie może wykonać ruchu!");
        });

        PlayerToken token = new PlayerToken(this.player, this.board);

        player.assignToken(token);

        System.out.println("Player has joined the game and their token is placed.");
    }

    public void start() {
        System.out.println("--- Game Started! ---");

        this.board.placeToken(3, 2, new GoldToken(5.0));

        // pyrite
        this.board.placeToken(6, 1, new PyriteToken());

        // gold
        this.board.placeToken(1, 1, new GoldToken(1.5));

        // pickaxe
        this.board.placeToken(1, 2, new PickaxeToken());

        // water token: dodajemy żeton wody na planszę
        this.board.placeToken(5, 5, new WaterToken(20)); // Przykład: żeton uzupełnia 20% nawodnienia

        PlayerToken playerToken = player.token();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();

            // Wyświetlanie aktualnego stanu nawodnienia
            System.out.printf("Current Gold: %.2f oz | Hydration: %d/100 | ",
                    player.gold.amount(),
                    player.vitals.hydration());
            System.out.println("Move (w/a/s/d): (or 'q' to quit)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("--- Game Ended! Final Gold: " + player.gold.amount() + " oz ---");
                break;
            }

            try {
                switch (input) {
                    case "a" -> playerToken.move(PlayerToken.Move.LEFT);
                    case "d" -> playerToken.move(PlayerToken.Move.RIGHT);
                    case "w" -> playerToken.move(PlayerToken.Move.UP);
                    case "s" -> playerToken.move(PlayerToken.Move.DOWN);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.err.println("Chwilowo nie żyjesz: " + e.getMessage() + ". Nie możesz wykonać ruchu!");
            }
        }
    }
}