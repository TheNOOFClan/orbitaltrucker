package com.github.thenoofclan.orbitaltrucker.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ship
{
    public float x;
    public float y;
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
        this.dir = dir;

        this.vel = new Vector2(0, 0);

        this.straightImg = straight;
        this.angleImg = angle;

        sprite0 = new Sprite(straightImg);
        sprite45 = new Sprite(angleImg);

        sprite0.setPosition((int) x, (int) y);
        sprite0.setRotation(dir);
        sprite45.setPosition((int) x, (int) y);
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
        sprite0.setPosition((int) x, (int) y);
        sprite45.setPosition((int) x, (int) y);
    }
}
