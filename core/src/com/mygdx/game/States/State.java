package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by m on 2/6/16.
 */
public abstract class State {
    //Put whatever you want each state to have here.
    //Things like a camera for example.
    public GSM gsm;

    public State(GSM gsm) {
        this.gsm = gsm;
    }


    protected abstract void handleInput();
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public void moveCommand(float x, float y, float z){
    }
    public void circleCommand(boolean isclockwise, double sweptAngle, float x, float y)
    {
    }
    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y){

    }
}
