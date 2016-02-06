package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by m on 2/6/16.
 */
public class MenuState extends State {

    private Texture background;

    public MenuState(GSM gsm) {
        super(gsm);
        background = new Texture("purple.jpg");
    }

    public void handleInput() {
        //Should make this able to communicate with LeapMotion.
        if (Gdx.input.justTouched()) {
            //DO THIS--> gsm.set(new PlayState(gsm));
            //Should add this later if we have images -->
            //dispose();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, 500, 500);
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
