package com.github.thenoofclan.orbitaltrucker;

import com.github.thenoofclan.orbitaltrucker.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrbitalTrucker extends Game
{
    public static final int SCALE = 2;

    public SpriteBatch batch;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }
}
