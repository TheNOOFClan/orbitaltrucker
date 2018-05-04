package com.github.thenoofclan.orbitaltrucker.screens;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.thenoofclan.orbitaltrucker.OrbitalTrucker;
import com.github.thenoofclan.orbitaltrucker.objects.PlayerTruck;
import com.github.thenoofclan.orbitaltrucker.objects.Ship;
import com.github.thenoofclan.orbitaltrucker.objects.SpaceObject;
import com.github.thenoofclan.orbitaltrucker.util.JsonReader.JsonObject;

public class StarSystemScreen implements Screen
{
	final OrbitalTrucker game;
	
	boolean isPaused;
	
	OrthographicCamera camera;
	
	PlayerTruck player;
	
	private SpaceObject[] obj;
	private ArrayList<Ship> ships;
	
	public StarSystemScreen(final OrbitalTrucker game, JsonObject universe)
	{
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 256, 240);
		
		readUniverse(universe);
		
		isPaused = false;
	}
	
	// might want to keep this in DesktopLauncher, or better yet, somewhere in util
	// later
	// TODO
	private void readUniverse(JsonObject u)
	{
		// hoo bloy, here we go
		JsonObject[] stars = u.toArray(); // an array of star systems
		for (JsonObject j : stars)
		{
			Map<String, JsonObject> system = j.toMap(); // a star system - a map of names and attributes
			Map<String, JsonObject> contents = system.get("contents").toMap(); // stars, planets, ships
			JsonObject[] bodies = contents.get("bodies").toArray(); // array of celestial bodies
			JsonObject[] sShips = contents.get("ships").toArray(); // array of ships
			obj = new SpaceObject[bodies.length];
			ships = new ArrayList<Ship>();
			for (int i = 0; i < bodies.length; i++)
			{
				obj[i] = new SpaceObject(bodies[i]);
			}
			for (JsonObject s : sShips)
			{
				Map<String, JsonObject> ship = s.toMap();
				String faction = ship.get("faction").toString();
				Ship current;
				if (faction.equals("player"))
				{
					player = new PlayerTruck(s, camera);
					current = player;
				} else
				{
					current = new Ship(s);
				}
				ships.add(current);
			}
		}
	}
	
	@Override
	public void show()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			isPaused = !isPaused;
		
		if (!isPaused)
		{
			for (Ship s : ships)
			{
				s.update();
			}
			for (SpaceObject o : obj)
			{
				o.update();
			}
		}
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		for (SpaceObject o : obj)
		{
			o.render(game.batch);
		}
		for (Ship s : ships)
		{
			s.render(game.batch);
		}
		player.render(game.batch);
		game.batch.end();
	}
	
	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose()
	{
		for (Ship s : ships)
			s.dispose();
		
		for (SpaceObject o : obj)
			o.dispose();
	}
	
}
