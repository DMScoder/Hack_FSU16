package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.*;

public class MainLeap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Controller controller;
	SampleListener listener;
	static Hand hand;
	static HandList hands;

	static class SampleListener extends Listener{

		public void onConnect(Controller controller)
		{
			System.out.println("Connected");
		}

		public void onFrame(Controller controller)
		{
			hands = controller.frame().hands();
			hand = hands.get(0);
		}
	}

	@Override
	public void create () {
		listener = new SampleListener();
		controller = new Controller();
		controller.addListener(listener);
		batch = new SpriteBatch();
		img = new Texture("core/assets/badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img,hand.palmPosition().getX()+100,-hand.palmPosition().getZ());
		batch.end();
	}

	public void dispose()
	{

	}
}
