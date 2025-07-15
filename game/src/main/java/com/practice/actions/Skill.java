package com.practice.actions;

import com.practice.entities.Entity;

public abstract class Skill {
    public String skillAnimation;
    public long cooldown;
    public long timeActivated = 0;
    public long cooldownComplete;

    public void activate(Entity player) {
        timeActivated = System.currentTimeMillis();
        cooldownComplete = (long) (timeActivated + cooldown);
        skillAbility(player);
    }   
    
    protected abstract void skillAbility(Entity player);
    protected abstract void skillEnd(Entity player);

}
