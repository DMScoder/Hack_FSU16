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
    Util util = new Util();
    long ticks = 0;

    float dx = 1;
    float dy = 2;

    private double x,y,h,w;

    public Bomb(float x, float y, PlayState state)
    {
        super(x,y,texture);
        this.playState = state;

    }

    public void update()
    {
        //ticks++;
        Hero hero = null;
        Bomb bomb = null;
        Ledge ledge = null;
        int bombI = 0;
        ArrayList<Entity> entities = playState.getEntity();

        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Hero) {
                hero = (Hero) entities.get(i);
            }

            if (entities.get(i) instanceof Ledge) {
                ledge = (Ledge) entities.get(i);
                if (Util.checkCollision(this, ledge)) {
                    this.removeFlag = true;

                    break;
                }
            }
        }


        if (this != null && hero != null && Util.checkCollision(this, hero)) {
            this.removeFlag = true;

        }

        if(this.removeFlag == false) {
            this.setX(this.getX() - 3);
            this.setY(this.getY() - 4);
        }
    }
}
