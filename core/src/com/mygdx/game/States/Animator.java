package com.mygdx.game.States;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Entity;
import com.mygdx.game.Hero;

public class Animator {

    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;
    Animation walkAnimation;
    //Contains entire sheet as single image.
    Texture walkSheet;
    //Will hold every frame in an array seperately.
    TextureRegion[] walkFrames;
    SpriteBatch spriteBatch;
    //Helps us keep track of what frame to render.
    TextureRegion currentFrame;
    float stateTime;


    public void create() {
        //Get entire sheet,
        walkSheet = new Texture(Gdx.files.internal("manSheet.png"));
        //2D array to help parse sheet.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        //(time allocated per frame, array containing all frames)
        walkAnimation = new Animation(0.025f, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    public void resize(int width, int height) {

    }

    public void render(Hero hero) {
        //Adds up delta time.
        stateTime += Gdx.graphics.getDeltaTime();
        //We use the dt to obtain the current frame
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, hero.getX(), hero.getY());
        spriteBatch.end();
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}