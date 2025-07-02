package com.practice.buffs;

import com.practice.entities.Entity;

public abstract class Buff {
    public long duration = 0;
    public Entity player;

    public void Buff() {

    }

    public abstract void activateBuff (Entity player);
}
