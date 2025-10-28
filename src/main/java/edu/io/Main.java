package edu.io;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();
    }
}