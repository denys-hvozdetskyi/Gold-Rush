package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {
    private int col;
    private int row;
    private final Board board;

    public enum Move {
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public record Coords(int col, int row) {}

    public PlayerToken(Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;
        board.placeToken(col, row, this);
    }

    // 🧭 Метод, який повертає поточні координати гравця
    public Coords pos() {
        return new Coords(col, row);
    }

    public void move(Move dir) {
        int newCol = col;
        int newRow = row;

        switch (dir) {
            case LEFT -> newCol -= 1;
            case RIGHT -> newCol += 1;
            case UP -> newRow -= 1;
            case DOWN -> newRow += 1;
            default -> {}
        }

        // перевірка меж
        if (newCol < 0 || newCol >= board.size || newRow < 0 || newRow >= board.size) {
            throw new IllegalArgumentException("Cannot move outside the board!");
        }

        // очистити старе поле
        board.placeToken(col, row, new EmptyToken());

        // оновити позицію
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}