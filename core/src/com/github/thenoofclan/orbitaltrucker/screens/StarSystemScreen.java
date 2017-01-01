package com.github.thenoofclan.orbitaltrucker.screens;

import com.github.thenoofclan.orbitaltrucker.OrbitalTrucker;

import com.github.thenoofclan.orbitaltrucker.objects.PlayerTruck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class StarSystemScreen implements Screen
{
    final OrbitalTrucker game;

    boolean isPaused;

    OrthographicCamera camera;

    Texture truck0;
    Texture truck45;
    Texture star;

    PlayerTruck player;

    public StarSystemScreen(final OrbitalTrucker game)
    {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 256, 240);

        truck0 = new Texture(Gdx.files.internal("truck0.png"));
        truck45 = new Texture(Gdx.files.internal("truck45.png"));
        star = new Texture(Gdx.files.internal("star.png"));

        player = new PlayerTruck(128 - 8, 120 - 8, 45, truck0, truck45, camera);

        isPaused = false;
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
            player.update();
        }

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(star, 0, 0);
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
        truck0.dispose();
        truck45.dispose();
    }

}
