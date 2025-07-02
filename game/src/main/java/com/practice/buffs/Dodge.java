package com.practice.buffs;

import com.practice.entities.Entity;

public class Dodge extends Buff {
    
    public Dodge() {
        this.duration = 650;
    }

    @Override
    public void activateBuff (Entity player) {
        player.setSpeed(1.5f);
    }
}
