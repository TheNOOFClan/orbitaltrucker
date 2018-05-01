package com.github.thenoofclan.orbitaltrucker.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceObject
{
	float x;
	float y;
	Texture texture;
	
	private Sprite sprite;
	
	public SpaceObject(String name, float x, float y, Texture texture)
	{
		sprite = new Sprite(texture);
		sprite.setPosition(x, y);
	}
	
	public void update()
	{
		// TODO method stub
	}
	
	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
}
