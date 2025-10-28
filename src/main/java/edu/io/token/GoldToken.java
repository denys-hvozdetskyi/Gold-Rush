package edu.io.token;

public class GoldToken extends Token {
    private final double amount;

    public GoldToken() {
        this(1.0); // Default amount
    }

    public GoldToken(double amount) {
        super(Label.GOLD_TOKEN_LABEL);
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}