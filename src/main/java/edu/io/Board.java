package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    public final int size;
    public final Token[][] grid;

    public Board() {
        this.size = 8;
        // this.size = size; upgrade for future
        this.grid = new Token[size][size];
        clean();
    }

    public record Coords(int col, int row) {}

    // Returns the size
    public int size() {
        return size;
    }

    // Reset the board
    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new EmptyToken();
            }
        }
    }

    // Place Token on Board
    public void placeToken(int col, int row, Token token) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            grid[row][col] = token;
        }
    }

    // Returns the Token located on coordinates from parameters
    public Token peekToken(int col, int row) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return grid[row][col];
        }
        return null;
    }

    // Display the Token
    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col].label() + " ");
            }
            System.out.println();
        }
    }
}

