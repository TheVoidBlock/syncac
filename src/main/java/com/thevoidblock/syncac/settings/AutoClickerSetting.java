package com.thevoidblock.syncac.settings;

public class AutoClickerSetting {
    public boolean active;
    public int timeElapsed = 0;
    public int cooldown;
    public int syncCooldown;
    public Runnable action;

    public AutoClickerSetting(int cooldown, boolean active, Runnable action) {
        this.active = active;
        this.cooldown = cooldown;
        this.syncCooldown = cooldown;
        this.action = action;
    }
}
