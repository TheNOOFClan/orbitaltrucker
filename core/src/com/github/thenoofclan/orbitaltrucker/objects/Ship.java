package com.github.thenoofclan.orbitaltrucker.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/** Generic Ship class */
public class Ship
{
    /** Position in X axis */
    public float x;
    /** Position in Y axis */
    public float y;
    /** Rotation in multiples of 45 degrees */
    public float dir;

    /** Velocity vector */
    public Vector2 vel;

    /** Texture for 0 degrees */
    public Texture straightImg;
    /** Texture for 45 degrees */
    public Texture angleImg;

    /** Sprite for 0 degrees */
    Sprite sprite0;
    /** Sprite for 45 degrees */
    Sprite sprite45;

    /**
     * Initializer method for a Ship type
     * @param x Initial position in the X axis
     * @param y Initial position in the Y axis
     * @param dir Initial Rotation
     * @param straight Texture for 0 degrees
     * @param angle Texture for 45 degrees
     */
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

    /**
     * Specific render method to properly render a Ship
     * @param batch Sprites used for the Ship
     */
    public void render(SpriteBatch batch)
    {
        Sprite toDraw = sprite45;
        if (dir % 2 == 0)
            toDraw = sprite0;
        toDraw.draw(batch);
    }

    /**
     * Specific update method to properly update a Ship
     */
    public void update()
    {
        x += vel.x;
        y += vel.y;
        sprite0.setPosition((int) x, (int) y);
        sprite45.setPosition((int) x, (int) y);
    }
}
