package com.github.thenoofclan.orbitaltrucker;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.thenoofclan.orbitaltrucker.screens.MainMenuScreen;
import com.github.thenoofclan.orbitaltrucker.util.JsonReader;
import com.github.thenoofclan.orbitaltrucker.util.JsonReader.JsonObject;

public class OrbitalTrucker extends Game
{
	public static final int SCALE = 2;
	
	public SpriteBatch batch;
	
	@Override
	public void create()
	{
		JsonReader j;
		JsonObject universe = null;
		try
		{
			j = new JsonReader("u.json");
			universe = j.parse();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this, universe));
	}
	
	@Override
	public void render()
	{
		super.render();
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
