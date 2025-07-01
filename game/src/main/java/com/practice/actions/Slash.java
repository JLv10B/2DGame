package com.practice.actions;

import static com.practice.utilz.Constants.PlayerConstants.SLASHING;
import static com.practice.utilz.Constants.PlayerConstants.RUN_SLASHING;

import com.practice.entities.*;

public class Slash extends Skill {


    public Slash() {
        this.skillAnimation = SLASHING;
        this.cooldown = 2000;
    }

    @Override
    //TODO: Currently only changes animation
    protected void skillAbility(Entity player) {
        if (player.moving() == true) {
            skillAnimation = RUN_SLASHING;
        } else {
            skillAnimation = SLASHING;
        }
    }

    public void skillEnd(Entity player) {
        
    }

    
}
