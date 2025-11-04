package edu.io.player;

import edu.io.Repairable;
import edu.io.Tool;
import edu.io.token.*;

public class Player {
    private PlayerToken token;
    private Tool pickaxeToken = new EmptyToken();
    private final Shed shed = new Shed();

    public final Gold gold = new Gold();

    public void assignToken(PlayerToken playerToken) {
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
        switch(token) {
            case GoldToken goldToken -> usePickaxeOnGold(goldToken);
            case PickaxeToken newPickaxeToken -> {
                if (!(this.pickaxeToken instanceof EmptyToken)) {
                    shed.add(this.pickaxeToken);
                }
                this.pickaxeToken = newPickaxeToken;
                System.out.println("New Pickaxe acquired.");
            }
            case AnvilToken anvilToken -> {
                if (this.pickaxeToken instanceof Repairable repairableTool) {
                    repairableTool.repair();
                }
            }
            default -> {}
        }
    }
}