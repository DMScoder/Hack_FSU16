package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.States.PlayState;

import java.util.ArrayList;

/**
 * Created by Daniel on 2/6/2016.
 */
public class Bomb extends Entity{

    public static Texture texture;
    private float gravity = -.5f;
    private PlayState playState;
    long ticks = 0;

    public Bomb(float x, float y, PlayState state)
    {
        super(x,y,texture);
        this.playState = state;

    }

    public void update()
    {
        ticks++;

        ArrayList<Entity> entries = playState.getEntity();

    }
}
