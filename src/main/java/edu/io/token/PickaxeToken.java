package edu.io.token;

import edu.io.Repairable;
import edu.io.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {

    private final Double gainFactor;
    private int durability;
    private final int maxDurability;
    private Token withToken;

    public PickaxeToken() {
        this(1.5, 3);
    }
    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }
    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Gain factor must be positive.");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive.");
        }
        this.gainFactor = gainFactor;
        this.durability = durability;
        this.maxDurability = durability;
    }
    public Double gainFactor() {
        return gainFactor;
    }
    public int durability() {
        return durability;
    }
    @Override
    public void repair() {
        this.durability = this.maxDurability;
    }
    public void use(){
        this.durability -= 1;
    }
    @Override
    public boolean isBroken() {
        return this.durability <= 0;
    }

    @Override
    public Tool useWith(Token token) {
        this.withToken = token;
        this.use();
        return this;
    }
    @Override
    public Tool ifWorking(Runnable action) {
        if (!this.isBroken() && this.withToken instanceof GoldToken) {
            action.run();
        }
        return this;
    }
    @Override
    public Tool ifBroken(Runnable action) {
        if(this.isBroken()) action.run();
        return this;
    }
    @Override
    public Tool ifIdle(Runnable action) {
        return this;
    }

}

