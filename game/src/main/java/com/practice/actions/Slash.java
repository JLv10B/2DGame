package com.practice.actions;

import com.practice.entities.*;

public class Slash extends Skill {
    public String skillAnimation;
    private int cooldown = 200;

    public Slash() {}

    @Override
    public String activate(Player player) {
        if (currentCooldown == 0) {
            if (player.moving() == true) {
                skillAnimation = "Run Slashing";
            } else {
                skillAnimation = "Slashing";
            }
            currentCooldown = cooldown;
            return skillAnimation;
        } else {
            System.out.println("Slash on cooldown");
            return "Run"; //TODO: should this still return run as a string?
        }
    }

    //TODO: The animation method only tells the player object to produce an animation. Just used for testing.
    public String animation(Player player) {
        if (currentCooldown == 0) {
            if (player.moving() == true) {
                skillAnimation = "Run Slashing";
            } else {
                skillAnimation = "Slashing";
            }
            currentCooldown = cooldown;
            return skillAnimation;
        } else {
            System.out.println("Slash on cooldown");
            return "Run"; //TODO: should this still return run as a string?
        }
    }
    
}
