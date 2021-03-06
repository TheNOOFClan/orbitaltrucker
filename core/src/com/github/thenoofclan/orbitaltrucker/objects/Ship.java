package com.github.thenoofclan.orbitaltrucker.objects;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.thenoofclan.orbitaltrucker.util.JsonReader.JsonObject;

public class Ship
{
	public float x;
	public float y;
	private int widthOffset;
	private int heightOffset;
	private int widthOffset45;
	private int heightOffset45;
	public float dir;
	
	public Vector2 vel;
	
	public Texture straightImg;
	public Texture angleImg;
	
	Sprite sprite0;
	Sprite sprite45;
	
	public Ship(float x, float y, float dir, Texture straight, Texture angle)
	{
		this.x = x;
		this.y = y;
		this.dir = dir * 45;
		
		this.widthOffset = straight.getWidth() / 2;
		this.heightOffset = straight.getHeight() / 2;
		this.widthOffset45 = angle.getWidth() / 2;
		this.heightOffset45 = angle.getHeight() / 2;
		
		this.vel = new Vector2(0, 0);
		
		this.straightImg = straight;
		this.angleImg = angle;
		
		sprite0 = new Sprite(straightImg);
		sprite45 = new Sprite(angleImg);
		
		sprite0.setPosition((int) x - widthOffset, (int) y - heightOffset);
		sprite0.setRotation(dir);
		sprite45.setPosition((int) x - widthOffset45, (int) y - heightOffset45);
		sprite45.setRotation(dir - 45);
	}
	
	public Ship(JsonObject definition)
	{
		Map<String, JsonObject> converted = definition.toMap();
		x = converted.get("x").toFloat();
		y = converted.get("y").toFloat();
		dir = converted.get("dir").toInt() * 45;
		
		this.vel = new Vector2(0, 0);
		
		String texturePath = converted.get("texture").toString();
		String texture45path = converted.get("texture45").toString();
		
		straightImg = new Texture(Gdx.files.internal(texturePath));
		angleImg = new Texture(Gdx.files.internal(texture45path));
		
		this.widthOffset = straightImg.getWidth() / 2;
		this.heightOffset = straightImg.getHeight() / 2;
		this.widthOffset45 = angleImg.getWidth() / 2;
		this.heightOffset45 = angleImg.getHeight() / 2;
		
		sprite0 = new Sprite(straightImg);
		sprite45 = new Sprite(angleImg);
		
		sprite0.setPosition((int) x - widthOffset, (int) y - heightOffset);
		sprite0.setRotation(dir);
		sprite45.setPosition((int) x - widthOffset45, (int) y - heightOffset45);
		sprite45.setRotation(dir - 45);
	}
	
	public void render(SpriteBatch batch)
	{
		Sprite toDraw = sprite45;
		if (dir % 2 == 0)
			toDraw = sprite0;
		toDraw.draw(batch);
	}
	
	public void update()
	{
		x += vel.x;
		y += vel.y;
		sprite0.setPosition((int) x - widthOffset, (int) y - heightOffset);
		sprite45.setPosition((int) x - widthOffset45, (int) y - heightOffset45);
	}
	
	public void dispose()
	{
		straightImg.dispose();
		angleImg.dispose();
	}
}
