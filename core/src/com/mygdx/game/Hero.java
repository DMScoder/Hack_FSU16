package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.States.Animator;
import com.mygdx.game.States.PlayState;

import java.util.ArrayList;

/**
 * Created by Damian Suski on 2/6/2016.
 */
public class Hero extends Entity{

    public static Texture texture;
    long ticks = 0;
    long lastJetPack = 0;
    private float dx = 0;
    private float dy = 0;
    private float gravity = -.5f;
    private float orientation = 1;
    boolean onLedge = false;
    private PlayState playState;

    public Hero(float x, float y, PlayState state)
    {
        super(x,y,texture);
        this.playState = state;
        //have to deal with collision in a new way.
    }

    public void update()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.F))
            reverseGravity();
        move();

        ticks++;

        ArrayList<Entity> entities =  playState.getEntity();
        Entity ledge = null;
        onLedge = false;

        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Ledge) {
                ledge = entities.get(i);
                if(Util.checkCollision(this, ledge))
                    if(orientation>0) {
                        if ((this.getY() <= (ledge.getY() + ledge.getHeight()))
                                && !Gdx.input.isKeyPressed(Input.Keys.S)) {
                            onLedge = true;
                            dy = 0;
                            dx = -2;
                            this.setY(ledge.getY() + ledge.getHeight());
                            gravity = 0;
                        }
                    }
                    else
                    {
                        if(this.getY()+this.getHeight() >= ledge.getY()&& !Gdx.input.isKeyPressed(Input.Keys.S)){
                            onLedge = true;
                            dy=0;
                            dx = -2;
                            this.setY(ledge.getY() - this.getHeight());
                            gravity = 0;
                        }
                    }
                }
            }
        if(onLedge == false)
        {
            gravity = -.5f*orientation;
        }
    }

    public void reverseGravity()
    {
        orientation*=-1;
    }

    private void move()
    {
        if((Gdx.input.isKeyPressed(Input.Keys.W)&&onLedge))
        {
            dy += 10*orientation;// - Math.abs(gravity);
        }
        else if((Gdx.input.isKeyPressed(Input.Keys.SPACE)&&lastJetPack<=ticks))
        {
            lastJetPack = ticks + 60;
            dy += 15*orientation;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)&&onLedge)
        {
            dy-= 10*orientation;// + Math.abs(gravity);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            dx-=2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            dx+=2;
        }

        dy+=gravity;

        if(!(this.getX()+dx<Gdx.graphics.getWidth())||!(this.getX()+dx>0))
            this.setX(Math.abs(Gdx.graphics.getWidth()-this.getX()));
        else
            this.setX(this.getX()+dx);

        if(!(this.getY()+dy<Gdx.graphics.getHeight())||!(this.getY()+dy>0))
            this.setY(Math.abs(Gdx.graphics.getHeight()-this.getY()));
        else
            this.setY(this.getY()+dy);

        if(!onLedge)
            dx/=1.5;
        //dy/=1.1;
    }
}
