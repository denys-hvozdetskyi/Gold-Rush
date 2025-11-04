package edu.io.player;

public class Gold {

    private double amount;

    public Gold() {
        this(0.0);
    }
    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative.");
        }
        this.amount = amount;
    }

    public double amount() {
        return this.amount;
    }

    public void gain(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot gain a negative amount of gold.");
        }
        this.amount += amount;
    }

    public void lose(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot lose a negative amount of gold.");
        }
        if (this.amount < amount) {
            throw new IllegalArgumentException("Cannot lose " + amount + " oz of gold. Player only has " + this.amount + " oz.");
        }
        this.amount -= amount;
    }
}
