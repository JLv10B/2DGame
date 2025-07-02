package com.practice.actions;

import static com.practice.utilz.Constants.PlayerConstants.SLIDING;

import com.practice.entities.Entity;
import com.practice.buffs.*;

public class Slide extends Skill{
    public Buff buff = new Dodge();

    public Slide() {
        this.skillAnimation = SLIDING;
        this.cooldown = 5000;
    }

    @Override
    public boolean activate(Entity player) {
        if (currentCooldown == 0) {
            timeActivated = System.currentTimeMillis();
            timeComplete = timeActivated + buff.duration;
            player.updateSkillCooldown(this, timeComplete);
            player.setActiveBuff(buff, timeComplete);
            return true;
        } else {
            return false;
        }
    } 

    @Override
    //TODO: Currently only changes animation
    protected void skillAbility(Entity player) {
    }

    @Override
    protected void skillEnd(Entity player) {
    }
    
}