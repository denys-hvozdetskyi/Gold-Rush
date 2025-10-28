package edu.io.token;

import edu.io.Board;
import edu.io.Player;

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

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.player = player;

        Board.Coords start = board.getAvailableSquare();
        this.col = start.col();
        this.row = start.row();

        board.placeToken(this.col, this.row, this);
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

        // If on target place
        Token targetToken = board.peekToken(newCol, newRow);

        // interaction
        player.interactWithToken(targetToken);

        // Clear previous field
        board.placeToken(oldCol, oldRow, new EmptyToken());

        // Update position
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}