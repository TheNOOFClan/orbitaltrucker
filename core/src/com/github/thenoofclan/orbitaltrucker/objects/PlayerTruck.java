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

    public PlayerTruck(float x, float y, float dir, Texture straight, Texture angle, OrthographicCamera camera)
    {
        super(x, y, dir, straight, angle);
        this.camera = camera;
    }

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
