package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Damian Suski on 2/7/2016.
 */
public class Powerup extends Entity{

    public static Texture texture;

    public Powerup(float x, float y)
    {
        super(x,y,texture);
    }

    public void update()
    {
        this.setX(this.getX()-2f);
    }
}
