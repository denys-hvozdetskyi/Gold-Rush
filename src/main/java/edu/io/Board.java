package edu.io;

public class Board {
    private int size;
    private Token[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
    }
}

