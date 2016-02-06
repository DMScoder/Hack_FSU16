package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Ledge extends Entity{

    public static Texture texture;

    public Ledge(float x, float y)
    {
        super(x,y,texture);
    }

    public void update()
    {
        this.setX(this.getX()-2f);
        if(this.getX()<-100f)
            this.setX(Gdx.graphics.getWidth());
    }

}
