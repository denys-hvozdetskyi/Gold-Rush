package edu.io;

public class Board {
    private final int size;
    private final Token[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    // Reset the board
    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Token("・");
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
    public Token square(int col, int row) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return grid[row][col];
        }
        return null;
    }

    // Display the Token
    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col].label + " ");
            }
            System.out.println();
        }
    }
}

