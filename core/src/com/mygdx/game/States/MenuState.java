package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leapmotion.leap.Vector;

/**
 * Created by m on 2/6/16.
 */
public class MenuState extends State {

    boolean swiped = false;
    Sprite sprite;

    public MenuState(GSM gsm) {
        super(gsm);
        Texture texture = new Texture("menu.png");
        sprite = new Sprite(texture);
        sprite.setX(Gdx.graphics.getWidth()/2 - 450);
        sprite.setY(Gdx.graphics.getHeight()/2 - 250);
        sprite.scale(0.5f);
    }

    @Override
    public void handleInput() {
        ;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        Gdx.gl20.glClearColor(169f/255f,169f/255f,169f/255f,169f/255f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprite.draw(sb);
        //Put gesture function here instead of "justTouched".
        if (swiped) {
            gsm.set(new PlayState(gsm));
        }
        sb.end();
    }

    @Override
    public void swipeCommand(com.leapmotion.leap.Vector direction, float speed, float x, float y) {
        if(speed >0 )
        //super.swipeCommand(direction, speed, x, y);
        swiped = true;
        //gsm.set(new PlayState(gsm));
    }

    @Override
    public void dispose() {

    }

}
