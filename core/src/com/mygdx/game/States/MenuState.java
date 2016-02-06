package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by m on 2/6/16.
 */
public class MenuState extends State {

    public MenuState(GSM gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {
        ;
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl20.glClearColor(169f/255f,169f/255f,169f/255f,169f/255f);
        //Put gesture function here instead of "justTouched".
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void dispose() {

    }

}
