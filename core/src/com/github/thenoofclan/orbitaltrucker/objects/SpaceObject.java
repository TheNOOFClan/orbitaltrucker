package com.github.thenoofclan.orbitaltrucker.objects;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.thenoofclan.orbitaltrucker.util.JsonReader.JsonObject;

public class SpaceObject
{
	float x;
	float y;
	private int widthOffset;
	private int heightOffset;
	
	Texture texture;
	
	private Sprite sprite;
	
	public SpaceObject(String name, float x, float y, Texture texture)
	{
		this.widthOffset = texture.getWidth() / 2;
		this.heightOffset = texture.getHeight() / 2;
		
		sprite = new Sprite(texture);
		sprite.setPosition((int) x - widthOffset, (int) y - heightOffset);
	}
	
	public SpaceObject(JsonObject definition)
	{
		Map<String, JsonObject> converted = definition.toMap();
		x = converted.get("x").toFloat();
		y = converted.get("y").toFloat();
		String texturePath = converted.get("texture").toString();
		texture = new Texture(Gdx.files.internal(texturePath));
		widthOffset = texture.getWidth() / 2;
		heightOffset = texture.getHeight() / 2;
		sprite = new Sprite(texture);
		sprite.setPosition((int) x - widthOffset, (int) y - heightOffset);
	}
	
	public void update()
	{
		// TODO method stub
	}
	
	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
	public void dispose()
	{
		texture.dispose();
	}
}
