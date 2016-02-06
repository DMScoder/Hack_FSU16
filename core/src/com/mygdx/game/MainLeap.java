package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.*;
import com.mygdx.game.States.GSM;

public class MainLeap extends ApplicationAdapter implements InputProcessor{

	SpriteBatch batch;
	GSM manager;
	LeapHandler leaphandler;

	@Override
	public void create () {
//		listener = new SampleListener();
//		controller = new Controller();
//		controller.addListener(listener);
		batch = new SpriteBatch();
		manager = new GSM();
		leaphandler = new LeapHandler(manager);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.95f, .95f, .8f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.render(batch);
	}

	public void dispose()
	{

	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE)
			Gdx.app.exit();

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
