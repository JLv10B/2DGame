package com.practice.actions;

import static com.practice.utilz.Constants.PlayerConstants.KICKING;

import com.practice.entities.Entity;

public class Kick extends Skill{

    public Kick() {
        this.skillAnimation = KICKING;
        this.cooldown = 5000;
    }

    @Override
    //TODO: Currently only changes animation
    protected void skillAbility(Entity player) {
        player.setMovementLock(true);

    }

    @Override
    //TODO: Currently only changes animation
    protected void skillEnd(Entity player) {
        player.setMovementLock(false);
    }
    
}