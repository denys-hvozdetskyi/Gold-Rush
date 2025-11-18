package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    public final int size;
    public final Token[][] grid;
    private int nextCol = 0;
    private int nextRow = 0;

    public Board() {
        this.size = 8;
        this.grid = new Token[size][size];
        clean();
    }

    public record Coords(int col, int row) {}

    public int size() {
        return size;
    }

    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new EmptyToken();
            }
        }
        nextCol = 0;
        nextRow = 0; // Reset search position
    }

    public void placeToken(int col, int row, Token token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        if (row >= 0 && row < size && col >= 0 && col < size) {
            grid[row][col] = token;
        }
    }

    public Token peekToken(int col, int row) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return grid[row][col];
        }
        return null;
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col].label() + " ");
            }
            System.out.println();
        }
    }

    public Coords getAvailableSquare() {
        // Search from remembered position to end
        for (int r = nextRow; r < size; r++) {
            int start = (r == nextRow) ? nextCol : 0;
            for (int c = start; c < size; c++) {
                if (grid[r][c] instanceof EmptyToken) {
                    // Update next search position
                    nextCol = c + 1;
                    nextRow = r;
                    if (nextCol >= size) {
                        nextCol = 0;
                        nextRow = r + 1;
                    }
                    return new Coords(c, r);
                }
            }
        }

        // Search from beginning to remembered position
        for (int r = 0; r < nextRow; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] instanceof EmptyToken) {
                    // Update next search position
                    nextCol = c + 1;
                    nextRow = r;
                    if (nextCol >= size) {
                        nextCol = 0;
                        nextRow = r + 1;
                    }
                    return new Coords(c, r);
                }
            }
        }

        throw new IllegalStateException("Board is full! Cannot place new tokens.");
    }
}