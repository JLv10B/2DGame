package com.practice.actions;

import com.practice.entities.Player;

public abstract class Skill {
    public int currentCooldown = 0;
    
    //TODO: The animation method only tells the player object to produce an animation. Just used for testing.
    public abstract String animation(Player player);

    public abstract String activate(Player player);

    public void update(double deltaTime) { // TODO: Implement cooldown timer
        double deltaU = 0;
        double timePerUpdate = 1000000000.0 / 120;
        long previousTime = System.currentTimeMillis();
        
        while (currentCooldown > 0) {
            long currentTime = System.currentTimeMillis();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;
            if (deltaU >= 1) {
                currentCooldown--;
                deltaU--;
            }
        }
    }

}
