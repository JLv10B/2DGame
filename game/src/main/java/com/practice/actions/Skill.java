package com.practice.actions;

import com.practice.entities.Entity;
import com.practice.entities.Player;

public abstract class Skill {
    public String skillAnimation;
    public double cooldown;
    public double currentCooldown = 0;
    public long timeActivated;

    public boolean activate(Entity player) {
        if (currentCooldown == 0) {
            skillAbility(player);
            timeActivated = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    public void updateTimers(Entity player) {
        long timeElapsed = System.currentTimeMillis() - timeActivated;
        currentCooldown = (cooldown - timeElapsed <= 0 ? 0 : cooldown - timeElapsed);
    }
    
    
    protected abstract void skillAbility(Entity player);
    protected abstract void skillEnd(Entity player);

}
