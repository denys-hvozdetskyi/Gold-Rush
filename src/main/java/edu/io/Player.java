package edu.io;

import edu.io.token.Token;
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

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
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot gain a negative amount of gold.");
        }
        this.gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot lose a negative amount of gold.");
        }
        if (this.gold < amount) {
            throw new IllegalArgumentException("Cannot lose " + amount + " oz of gold. Player only has " + this.gold + " oz.");
        }
        this.gold -= amount;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            this.gainGold(goldToken.amount());
        }
    }
}