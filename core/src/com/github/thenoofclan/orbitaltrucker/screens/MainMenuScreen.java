package com.github.thenoofclan.orbitaltrucker.screens;

import com.github.thenoofclan.orbitaltrucker.OrbitalTrucker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen
{

    final OrbitalTrucker game;

    OrthographicCamera camera;
    Texture img;

    public MainMenuScreen(final OrbitalTrucker game)
    {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 256, 240);

        img = new Texture(Gdx.files.internal("title.png"));
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

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(img, 128 - 80, 240 - 48);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Keys.ANY_KEY))
        {
            game.setScreen(new StarSystemScreen(game));
            dispose();
        }
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
        img.dispose();
    }

}
