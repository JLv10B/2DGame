package com.practice.actions;

import static com.practice.utilz.Constants.PlayerConstants.SLIDING;

import com.practice.entities.Entity;
import com.practice.buffs.*;

public class Slide extends Skill{
    public Buff buff = new Dodge();

    public Slide() {
        this.skillAnimation = SLIDING;
        this.cooldown = 2000;
    }

    @Override
    //TODO: Currently only adds speed buff
    protected void skillAbility(Entity player) {
        player.setActiveBuff(buff, timeActivated + buff.duration);
    }

    @Override
    protected void skillEnd(Entity player) {
    }
    
}