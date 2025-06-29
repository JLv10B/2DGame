package com.practice.entities;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import static com.practice.utilz.Constants.Action;

public abstract class Entity {
    protected float x,y;
    protected boolean movementLock;
    protected boolean skillLock = false;
    protected float xDir = 0.0f;
    protected float yDir = 0.0f;
    protected float speed = 1.0f;
    protected boolean left, right, up, down;
    
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void keybindOutput(Action action) {
        if (action == Action.UP) {
            up = true;
        } else if (action == Action.DOWN) {
            down = true;
        }else if (action == Action.LEFT) {
            left = true;
        } else if (action == Action.RIGHT) {
            right = true;
        }  
    }

    public void movementKeyRelease(Action action) {
        if (action == Action.UP) {
            up = false;
        } else if (action == Action.DOWN) {
            down = false;
        }else if (action == Action.LEFT) {
            left = false;
        } else if (action == Action.RIGHT) {
            right = false;
        }  
    }

    protected void updatePos() {
        xDir = ((left == right) || movementLock) ? 0 : (left ? -1 : 1);
        yDir = ((up == down) || movementLock) ? 0 : (up ? -1 : 1);
        if (moving()){
            float xVel = (xDir/(Math.abs(xDir) + Math.abs(yDir))) * speed;
            float yVel = (yDir/(Math.abs(xDir) + Math.abs(yDir))) * speed;
            x += xVel;
            y += yVel;
        }
    }

    public boolean moving() {
        return (xDir != 0 || yDir != 0);
    }

    // TODO: fix this to stop movement when window is out of focus, moving away from boolean movement
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        xDir = 0;
        yDir = 0;
    }
}
