package edu.io;

public class Board {
    private int size;
    private Token[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Token("・");
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            grid[row][col] = token;
        }
    }
}

