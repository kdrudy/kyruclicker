package com.kyrutech.clicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class KyruClicker extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	BitmapFont font;

	int clicks = 0;
	boolean clicked[] = new boolean[20];
	Vector2 clickedPoint[] = new Vector2[20];

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("explosion.png");
		font = new BitmapFont();
		font.getData().setScale(3, 3);
	}

	@Override
	public void render () {
		for(int i = 0;i<20;i++) {
			if (Gdx.input.isTouched(i) && !clicked[i]) {
				clicked[i] = true;
				clickedPoint[i] = new Vector2(Gdx.input.getX(i), Gdx.input.getY(i));
				clicks++;
			} else if (!Gdx.input.isTouched(i) && clicked[i]) {
				clicked[i] = false;
			}
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		font.draw(batch, "Clicks: " + clicks, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

		for(int i = 0;i<20;i++) {
			if(clicked[i]) {
				batch.draw(img, clickedPoint[i].x-250, Gdx.graphics.getHeight()-clickedPoint[i].y-250, 500, 500);
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
