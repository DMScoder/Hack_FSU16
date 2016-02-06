package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.States.PlayState;

import java.util.ArrayList;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Hero extends Entity{

    public static Texture texture;
    private float dx = 0;
    private float dy = 0;
    private float gravity = -3;
    private PlayState playState;
    private Util util = new Util();

    public Hero(float x, float y, PlayState state)
    {
        super(x,y,texture);
        this.playState = state;
    }

    public void update()
    {
        move();

        ArrayList<Entity> entities =  playState.getEntity();
        Entity ledge = null;
        boolean onLedge = false;

        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Ledge) {
                ledge = entities.get(i);

                if(Util.checkCollision(this, ledge) && (this.getY() <= (ledge.getY() + ledge.getHeight())) ) {
                    onLedge = true;
                    dy = 0;
                    this.setY(ledge.getY() + ledge.getHeight());
                    gravity = 0;
                }
            }
        }

        if(onLedge == false)
            gravity  = -3;



    }

    public void reverseGravity()
    {
        gravity*=-1;
    }

    private void move()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            dy +=5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            dy-=3;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            dx-=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            dx+=1;
        }

        dy+=gravity;

        if(this.getX()+dx<Gdx.graphics.getWidth()&&this.getX()+dx>0)
            this.setX(this.getX()+dx);

        if(this.getY()+dy<Gdx.graphics.getHeight()&&this.getY()+dy>0)
            this.setY(this.getY()+dy);

        dx/=1.05;
        dy/=1.3;
    }
}
