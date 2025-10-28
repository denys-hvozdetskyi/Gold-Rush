package edu.io.token;

import edu.io.Board;
import edu.io.Player; // Використовуйте edu.io.player.Player, якщо він у цьому пакеті

public class PlayerToken extends Token {
    private int col;
    private int row;
    private final Board board;
    private final Player player;

    public enum Move {
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    // 1. Головний конструктор: приймає всі параметри і виконує всю ініціалізацію
    public PlayerToken(Board board, Player player, int col, int row) {
        // 1. super() - ПЕРШИЙ ВИКЛИК!
        super(Label.PLAYER_TOKEN_LABEL);

        // 2. Ініціалізація полів
        this.board = board;
        this.player = player;
        this.col = col;
        this.row = row;

        // 3. Додаткова логіка
        board.placeToken(col, row, this);
    }

    public PlayerToken(Board board, Player player) {
        this(board, player, board.getAvailableSquare().col(), board.getAvailableSquare().row());
    }

    // Return current coordinates
    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    public void move(Move dir) {
        int newCol = col;
        int newRow = row;
        int oldCol = col;
        int oldRow = row;

        switch (dir) {
            case LEFT -> newCol -= 1;
            case RIGHT -> newCol += 1;
            case UP -> newRow -= 1;
            case DOWN -> newRow += 1;
            default -> {}
        }

        // Limit check
        if (newCol < 0 || newCol >= board.size || newRow < 0 || newRow >= board.size) {
            throw new IllegalArgumentException("Cannot move outside the board!");
        }

        // 2. ОБОВ'ЯЗКОВО: Перевіряємо, що знаходиться на цільовій клітинці
        Token targetToken = board.peekToken(newCol, newRow);

        // 3. ОБОВ'ЯЗКОВО: Взаємодія з фішкою, що знаходиться на цільовій клітинці.
        player.interactWithToken(targetToken);

        // Clear previous field
        board.placeToken(oldCol, oldRow, new EmptyToken()); // Потрібен import EmptyToken

        // Update position
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}