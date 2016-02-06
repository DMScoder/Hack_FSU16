package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by m on 2/6/16.
 */
public class MenuState extends State {

    private Texture background;

    public MenuState(GSM gsm) {
        super(gsm);
        background = new Texture("purple.jpg");
    }

    @Override
    public void handleInput() {
        ;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0 ,0, 500, 500 );
        sb.end();

        //Put gesture function here instead of "justTouched".
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void dispose() {

    }

}
