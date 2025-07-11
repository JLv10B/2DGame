package com.practice.entities;

import static com.practice.utilz.Constants.PlayerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.practice.actions.*;
import com.practice.buffs.*;
import com.practice.gamestates.Gamestate;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.LevelBuilder;
import com.practice.utilz.Constants.Action;
import static com.practice.utilz.HelperMethods.CanMoveHere;
import com.practice.gamestates.*;
import static com.practice.gamestates.Gamestate.MENU;


public abstract class Entity {
    protected String charModel = "1-Player-Dark Oracle";
    protected ImageLibrary imageLibrary;
    protected float x,y;
    protected int width, height;
    protected float xDir = 0.0f;
    protected float yDir = 0.0f;
    protected float charDefaultSpeed = 1.5f;
    protected float charSpeedDiff = 0.0f;
    protected boolean left, right, up, down;
    protected int aniTick, aniIndex, aniSpeed = 15;
    protected String playerAction = IDLE;
    public boolean movementLock;
    protected boolean skillLock;
    protected String skillAction = IDLE;
    protected Skill[] playerSkillBar = new Skill[3];
    protected HashMap<Buff, Long> buffBar = new HashMap<>();
    protected HashMap<Skill, Long> skillCooldowns = new HashMap<>();
    protected Rectangle2D.Float hitbox;
    protected int[][] levelData;
    // private float xDrawOffset = 244;
    // private float yDrawOffset = 210;

    

    public Entity(float x, float y, int width, int height, int[][] levelData, ImageLibrary imageLibrary) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.levelData = levelData;
        this.imageLibrary = imageLibrary;
        skillBarSetUp();
    }

    public void update() {
        buffCheck();
        updateTimers();
        updateHitbox();
        updatePos();
        updateAnimationTick();
        setAnimation();
        
    }
    
    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void drawHitbox(Graphics g) {
        // for debugging the hitbox
        g.setColor(Color.magenta);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void render(Graphics g) {
        g.drawImage(imageLibrary.getCharLibrary().get(charModel).get(playerAction).get(aniIndex), (int)x, (int)y, width, height,null);
        drawHitbox(g); // TODO: remove drawHitbox after testing is complete
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
        } else if (action == Action.MENU) {
            resetDirBooleans();
            Gamestate.state = MENU;
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
            if (CanMoveHere(x+xVel, y+yVel, width, height, levelData)) {
                x += xVel;
                y += yVel;
            }
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
                aniIndex = 0;
                skillLock = false;
                setMovementLock(false);
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
        //TODO: remove default skillbar once skillBarSetUp is created
        playerSkillBar[0] = new Slash();
        playerSkillBar[1] = new Kick();
        playerSkillBar[2] = new Slide();
        skillCooldowns.put(playerSkillBar[0], (long) 0);
        skillCooldowns.put(playerSkillBar[1], (long) 0);
        skillCooldowns.put(playerSkillBar[2], (long) 0);
    }

    // Checks if skill is on cooldown and changes the skillAnimation based off of player state.
    protected void skillActivation(int index) {
        if (playerSkillBar[index] != null && skillLock == false && skillCooldowns.get(playerSkillBar[index]) == (long) 0) {
            playerSkillBar[index].activate(this); 
            skillLock = true;
            skillAction = playerSkillBar[index].skillAnimation;
            skillCooldowns.put(playerSkillBar[index], playerSkillBar[index].cooldownComplete);
        }
    }

    protected void updateTimers() {
        if (skillCooldowns.size() > 0) {
            for (Map.Entry<Skill,Long> entry : skillCooldowns.entrySet()) {
                if (entry.getValue() <= System.currentTimeMillis()) {
                    Skill skill = entry.getKey();
                    skillCooldowns.put(skill, (long) 0);
                }
            }
        }
    }

    protected void buffCheck() {
        // Zero out all modifiers by default
        charSpeedDiff = 0;
        
        if (buffBar.size() > 0) {
            for (Map.Entry<Buff,Long> entry : buffBar.entrySet()) {
                if (entry.getValue() <= System.currentTimeMillis()) {
                    buffBar.remove(entry.getKey());
                } else {
                    entry.getKey().activateBuff(this);
                }
            }
        }
    }


    public void setActiveBuff(Buff buff,long endTime) {
        buffBar.put(buff, endTime);
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
