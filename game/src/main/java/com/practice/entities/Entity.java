package com.practice.entities;

import static com.practice.utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.util.ArrayList;

import com.practice.actions.*;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.Constants.Action;


public abstract class Entity {
    protected String charModel = "1-Player-Dark Oracle";
    protected ImageLibrary imageLibrary;
    protected float x,y;
    protected float xDir = 0.0f;
    protected float yDir = 0.0f;
    protected float charDefaultSpeed = 1.0f;
    protected float charSpeedDiff = 0.0f;
    protected boolean left, right, up, down;
    protected int aniTick, aniIndex, aniSpeed = 15;
    protected String playerAction = IDLE;
    public boolean movementLock;
    protected boolean skillLock;
    protected String skillAction = IDLE;
    protected Skill[] playerSkillBar = new Skill[3];
    protected ArrayList<Skill> activeSkills = new ArrayList<>();
    

    public Entity(float x, float y, ImageLibrary imageLibrary) {
        this.x = x;
        this.y = y;
        this.imageLibrary = imageLibrary;
    }

    public void update() {
        updateSkills();
        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(imageLibrary.getLibrary().get(charModel).get(playerAction).get(aniIndex), (int)x, (int)y, 150,150,null);
    }

    public void keybindOutput(Action action) {
        int skillBarIndex = 0;
        
        if (action == Action.UP) {
            up = true;
        } else if (action == Action.DOWN) {
            down = true;
        } else if (action == Action.LEFT) {
            left = true;
        } else if (action == Action.RIGHT) {
            right = true;
        } else if (action == Action.SKILL_1) {
            skillBarIndex = 0;
            skillActivation(skillBarIndex);
        } else if (action == Action.SKILL_2) {
            skillBarIndex = 1;
            skillActivation(skillBarIndex);
        } else if (action == Action.SKILL_3) {
            skillBarIndex = 2;
            skillActivation(skillBarIndex);
        }
    }

    public void movementKeyRelease(Action action) {
        if (action == Action.UP) {
            up = false;
        } else if (action == Action.DOWN) {
            down = false;
        } else if (action == Action.LEFT) {
            left = false;
        } else if (action == Action.RIGHT) {
            right = false;
        }
    }

    protected void updatePos() {
        xDir = ((left == right) || movementLock) ? 0 : (left ? -1 : 1);
        yDir = ((up == down) || movementLock) ? 0 : (up ? -1 : 1);
        if (moving()){
            float xVel = (xDir/(Math.abs(xDir) + Math.abs(yDir))) * (charDefaultSpeed + charSpeedDiff);
            float yVel = (yDir/(Math.abs(xDir) + Math.abs(yDir))) * (charDefaultSpeed + charSpeedDiff);
            x += xVel;
            y += yVel;
        }
    }

    public boolean moving() {
        return (xDir != 0 || yDir != 0);
    }

    protected void setAnimation() {
        String starting = playerAction;

        if (skillLock) {
            playerAction = skillAction;
        } else if (moving()) {
            playerAction = RUNNNING;
        } else {
            playerAction = IDLE;
        }
   
        if (starting != playerAction) {
            resetAnimationTick();
        }
   }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    protected void updateAnimationTick() {
          aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            int tmpInt = imageLibrary.getSpriteAmount(charModel, playerAction);
            if (aniIndex >= tmpInt) { 
                // System.out.println("aniIndex before reset: " + aniIndex + " for playerAction: " + playerAction);
                aniIndex = 0;
                skillLock = false;
                setMovementLock(false);
                // setSpeed(0);
            }
        }

    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        xDir = 0;
        yDir = 0;
    }

    protected void skillBarSetUp() {
        // TODO: Allow player to set up skill bar. Might need menu screen first
    }

    protected void skillActivation(int index) {
        //TODO: remove default skillbar once skillBarSetUp is created
        playerSkillBar[0] = new Slash();
        playerSkillBar[1] = new Kick();
        playerSkillBar[2] = new Slide();

        if (playerSkillBar[index] != null) {
            skillLock = playerSkillBar[index].activate(this); // Checks if skill is on cooldown and changes the skillAnimation based off of player state.
            skillAction = playerSkillBar[index].skillAnimation;
        }
    }

    protected void updateSkills() {
        for (Skill skill : activeSkills) {
            skill.updateTimers(this);
        }
    }

    public void setActiveSkill(Skill skill) {
        activeSkills.add(skill);        
    }

    public void removeActiveSkill(Skill skill) {
        activeSkills.remove(skill);
    }

    public void setMovementLock(boolean lock) {
        movementLock = lock;
    }

    public void setSkillLock(boolean lock) {
        skillLock = lock;
    }

    public void setSpeed(float speed) {
        charSpeedDiff = speed;
    }

}
