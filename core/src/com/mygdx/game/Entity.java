package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Entity extends Sprite {

    public boolean removeFlag = false;

    public Entity(float x, float y,Texture texture)
    {
        super(texture);
        this.setX(x);
        this.setY(y);
    }

    public void update()
    {
        if(this.getX()<-250)
            removeFlag = true;
    }

    public void render(SpriteBatch batch)
    {
        this.draw(batch);
    }
}
