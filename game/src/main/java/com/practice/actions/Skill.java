package com.practice.actions;

import com.practice.entities.Entity;
import com.practice.entities.Player;

public abstract class Skill {
    public String skillAnimation;
    public long cooldown;
    // public double currentCooldown = 0;
    public long timeActivated = 0;
    public long cooldownComplete;

    public void activate(Entity player) {
        timeActivated = System.currentTimeMillis();
        cooldownComplete = (long) (timeActivated + cooldown);
        skillAbility(player);
    }

    // public void resetCooldown() {
    //     currentCooldown = 0;
    // }

    // public void updateTimers() {
    //     if (timeActivated != 0) {
    //         long timeElapsed = System.currentTimeMillis() - timeActivated;
    //         currentCooldown = (cooldown - timeElapsed <= 0 ? 0 : cooldown - timeElapsed);
    //         if (currentCooldown == 0) {
    //             timeActivated = 0;
    //         }
    //     }
    // }
    
    
    protected abstract void skillAbility(Entity player);
    protected abstract void skillEnd(Entity player);

}
