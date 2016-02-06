package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by m on 2/6/16.
 */
public class GSM {

    private Stack<State> states;

    public GSM() {
        states = new Stack<State>();
        this.push(new MenuState(this));
    }

    public void push(State state) {
        states.push(state);
    }
    //Pop and dispose to avoid memory leaks.
    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        //Push in whatever new state we have.
        states.push(state);
    }

    //render to screen anything new we have.
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    public void moveCommand(float x, float y, float z)
    {
        states.peek().moveCommand(x, y, z);
    }
    public void circleCommand(boolean isclockwise, double sweptAngle, float x, float y)
    {
        states.peek().circleCommand(isclockwise, sweptAngle, x, y);
    }
    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y)
    {
        states.peek().swipeCommand(direction, speed, x, y);
    }
    public void duoSwipeDownCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y)
    {
        states.peek().duoSwipeDownCommand(direction, speed, x, y);
    }
    public void pinchCommand()
    {
        states.peek().pinchCommand();
    }
}
