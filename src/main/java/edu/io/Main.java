package edu.io;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gold Rush");

        Board board = new Board();

        Token player = new Token("웃");
        Token gold = new Token("\uD83D\uDCB0");

        board.clean();
        board.placeToken(7, 7, player);
        board.placeToken(0, 0, gold);

        board.display();
    }
}
