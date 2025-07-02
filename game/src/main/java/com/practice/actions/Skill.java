package com.practice.actions;

import com.practice.entities.Entity;
import com.practice.entities.Player;

public abstract class Skill {
    public String skillAnimation;
    public long cooldown;
    public double currentCooldown = 0;
    public long timeActivated = 0;
    public long timeComplete;

    public boolean activate(Entity player) {
        if (currentCooldown == 0) {
            skillAbility(player);
            timeActivated = System.currentTimeMillis();
            currentCooldown = cooldown;
            timeComplete = (long) (timeActivated + cooldown);
            return true;
        } else {
            return false;
        }
    }

    public void resetCooldown() {
        currentCooldown = 0;
    }

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
