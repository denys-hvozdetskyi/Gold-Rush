package edu.io;

import edu.io.Player;
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;

import java.util.Scanner;

public class Game {
    private final Board board;
    private Player player;

    // Конструктор: створює дошку, це мінімальна ініціалізація
    public Game() {
        this.board = new Board();
        // Примітка: Об'єкт Player створюється поза цим класом (у Main)
    }

    /**
     * Getter for the Board, needed for setting up test tokens in Main/Tests.
     */
    public Board board() {
        return this.board;
    }

    /**
     * Connects a Player to the Game, creates a PlayerToken for them,
     * and places the token on the board using getAvailableSquare().
     * @param player The player joining the game.
     */
    public void join(Player player) {
        this.player = player;

        // 1. Створюємо PlayerToken, передаючи йому Board та Player
        // Цей конструктор автоматично викликає board.getAvailableSquare()
        PlayerToken token = new PlayerToken(this.board, this.player);

        // 2. Призначаємо фішку гравцеві
        player.assignToken(token);

        System.out.println("Player has joined the game and their token is placed.");
    }

    /**
     * Contains the main game loop logic, as per the presentation requirements.
     */
    public void start() {
        System.out.println("--- Game Started! ---");

        this.board.placeToken(3, 2, new GoldToken(5.0));

        // Пірит (Золото дурнів): 0.0 унцій (має не збільшити золото гравця)
        this.board.placeToken(6, 1, new PyriteToken());

        // Золото: 1.5 унції
        this.board.placeToken(1, 1, new GoldToken(1.5));

        // Отримуємо фішку для зручності
        PlayerToken playerToken = player.token();
        Scanner scanner = new Scanner(System.in);

        // --- Тестове налаштування для демонстрації взаємодії ---
        // Якщо ми хочемо запустити ігровий цикл тут, ми розміщуємо тестові фішки
        // на початку, або ж це робить test runner (Main) перед викликом start().

        // Тут ми демонструємо, як може виглядати ігровий цикл:
        while (true) {
            board.display();

            System.out.printf("Current Gold: %.2f oz | ", player.gold());
            System.out.println("Move (w/a/s/d): (or 'q' to quit)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("--- Game Ended! Final Gold: " + player.gold() + " oz ---");
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
            }
        }
    }
}