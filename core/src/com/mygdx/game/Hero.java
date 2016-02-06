package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Hero extends Entity{

    public static Texture texture;
    private float dx = 0;
    private float dy = 0;

    public Hero(float x, float y)
    {
        super(x,y,texture);
    }

    public void update()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            dy +=10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            dy-=5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            dx-=5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            dx+=5;
        }

        dy-=3;

        if(this.getX()+dx<Gdx.graphics.getWidth()&&this.getX()+dx>0)
            this.setX(this.getX()+dx);

        if(this.getY()+dy<Gdx.graphics.getHeight()&&this.getY()+dy>0)
            this.setY(this.getY()+dy);

        dx=0;
        dy=0;
    }
}
