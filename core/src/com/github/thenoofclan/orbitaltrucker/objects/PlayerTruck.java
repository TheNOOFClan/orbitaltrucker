package com.github.thenoofclan.orbitaltrucker.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerTruck extends Ship
{

    OrthographicCamera camera;

    int turnTimeout = 0;
    int maxTurnTimeout = 30;
    int maxVel = 2;

    /**
     * Initializer for a PlayerTruck
     * @param x Initial position in X axis (Passed to Super)
     * @param y Initial position in Y axis (Passed to Super)
     * @param dir Initial rotation (Passed to Super)
     * @param straight Texture for 0 degrees (Passed to Super)
     * @param angle Texture for 45 degrees (Passed to Super)
     * @param camera Pass the camera, This is needed to reposition the camera
     */
    public PlayerTruck(float x, float y, float dir, Texture straight, Texture angle, OrthographicCamera camera)
    {
        super(x, y, dir, straight, angle);
        this.camera = camera;
    }

    /**
     * Specific update method to properly update a PlayerTruck
     */
    @Override
    public void update()
    {
        Vector2 acceleration = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.turnTimeout <= 0)
        {
            this.dir = (dir + 315) % 360;
            this.turnTimeout = maxTurnTimeout;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.turnTimeout <= 0)
        {
            this.dir = (dir + 45) % 360;
            this.turnTimeout = maxTurnTimeout;
        }
        sprite0.setRotation(this.dir);
        sprite45.setRotation(this.dir - 45);
        turnTimeout--;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            acceleration.set(1 * Gdx.graphics.getDeltaTime(), 0);
            acceleration.setAngle(this.dir);
        }
        this.vel.add(acceleration);
        if (this.vel.len() > this.maxVel)
            this.vel.setLength(this.maxVel);

        super.update();
        camera.position.x = (int) x;
        camera.position.y = (int) y;
    }

}
