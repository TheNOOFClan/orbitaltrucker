package com.github.thenoofclan.orbitaltrucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrbitalTrucker extends ApplicationAdapter
{
    public static final int SCALE = 2;

    SpriteBatch batch;
    Texture img;
    public OrthographicCamera camera;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        img = new Texture("title.png");

        camera = new OrthographicCamera(240 * SCALE, 256 * SCALE);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        img.dispose();
    }
}
