package edu.io.player;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Vitals {

    private int hydration;

    private Runnable onDeathCallback;

    public Vitals() {
        hydration = 100;
        onDeathCallback = () -> {};
    }

    public int hydration() {
        return hydration;
    }

    public boolean isAlive() {
        return hydration > 0;
    }

    public void hydrate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Hydration amount cannot be negative.");
        }

        this.hydration = Math.min(100, this.hydration + amount);
    }

    public void dehydrate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Dehydration amount cannot be negative.");
        }

        int oldHydration = this.hydration;

        this.hydration = Math.max(0, this.hydration - amount);

        if (this.hydration == 0 && oldHydration > 0) {
            onDeathCallback.run();
        }
    }

    public void setOnDeathHandler(@NotNull Runnable callback) {
        this.onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }
}