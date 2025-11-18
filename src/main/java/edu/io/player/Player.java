package edu.io.player;

import edu.io.Repairable;
import edu.io.Tool;
import edu.io.token.*;

public class Player {
    private PlayerToken token;
    private Tool pickaxeToken = new EmptyToken();
    private final Shed shed = new Shed();

    public final Gold gold = new Gold();
    public final Vitals vitals = new Vitals();

    public void assignToken(PlayerToken playerToken) {
        if (playerToken == null) {
            throw new NullPointerException("Token cannot be null");
        }
        this.token = playerToken;
    }

    public PlayerToken token() {
        return this.token;
    }

    private void usePickaxeOnGold(GoldToken goldToken) {
        final double baseAmount = goldToken.amount();
        this.pickaxeToken.useWith(goldToken)
                .ifWorking(() -> {
                    if (this.pickaxeToken instanceof PickaxeToken pf) {
                        gold.gain(baseAmount * pf.gainFactor());
                        pf.use();
                    }
                })
                .ifBroken(() -> {
                    gold.gain(baseAmount);
                    this.pickaxeToken = shed.getTool();
                })
                .ifIdle(() -> gold.gain(baseAmount));
    }

    public void interactWithToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        if (!vitals.isAlive()) {
            throw new IllegalStateException("player is dead");
        }

        switch(token) {
            case EmptyToken emptyToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }
            case PyriteToken pyriteToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                usePickaxeOnGold(pyriteToken);
            }
            case GoldToken goldToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                usePickaxeOnGold(goldToken);
            }
            case PickaxeToken newPickaxeToken -> {
                if (!(this.pickaxeToken instanceof EmptyToken)) {
                    shed.add(this.pickaxeToken);
                }
                this.pickaxeToken = newPickaxeToken;
                System.out.println("New Pickaxe acquired.");
            }
            case AnvilToken anvilToken -> {
                if (this.pickaxeToken instanceof Repairable repairableTool) {
                    vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                    repairableTool.repair();
                }
            }
            case WaterToken waterToken -> {
                vitals.hydrate(waterToken.amount());
            }
            default -> {}
        }
    }
}