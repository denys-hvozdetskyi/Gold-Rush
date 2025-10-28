package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {
    private PlayerToken token;
    private double gold = 0.0;

    public void assignToken(PlayerToken playerToken) {
        this.token = playerToken;
    }

    public PlayerToken token() {
        return this.token;
    }

    public double gold() {
        return this.gold;
    }

    public void gainGold(double amount) {
        this.gold += amount;
        System.out.println("Gained " + amount + " oz of gold. Total: " + this.gold);
    }

    public void loseGold(double amount) {
        this.gold -= amount;
        if (this.gold < 0) this.gold = 0;
        System.out.println("Lost " + amount + " oz of gold. Total: " + this.gold);
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken gold) {
            System.out.println("GOLD! Interaction!");
            this.gainGold(gold.amount());
        }
    }
}
