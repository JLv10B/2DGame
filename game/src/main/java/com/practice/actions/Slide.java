package com.practice.actions;

import static com.practice.utilz.Constants.PlayerConstants.SLIDING;

import com.practice.entities.Entity;

public class Slide extends Skill{
    private long duration = 2000;
    private long currentDuration = 0;

    public Slide() {
        this.skillAnimation = SLIDING;
        this.cooldown = 5000;
    }

    @Override
    public void updateTimers(Entity player) {
        long timeElapsed = System.currentTimeMillis() - timeActivated;
        currentCooldown = (cooldown - timeElapsed <= 0 ? 0 : cooldown - timeElapsed);
        currentDuration = (duration - timeElapsed <= 0 ? 0 : duration - timeElapsed);
        if (currentDuration <= 0) {
            skillEnd(player);
        }
       
    }

    @Override
    //TODO: Currently only changes animation
    protected void skillAbility(Entity player) {
        player.setSpeed(1);
    }

    @Override
    protected void skillEnd(Entity player) {
        player.setSpeed(0);
    }
    
}