package com.github.thenoofclan.orbitaltrucker;

import com.github.thenoofclan.orbitaltrucker.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrbitalTrucker extends Game
{
    public static final int SCALE = 2;

    public SpriteBatch batch;

    /**
     * Create method for the game
     */
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    /**
     * Calls the render method from Game
     */
    @Override
    public void render()
    {
        super.render();
    }

    /**
     * Calls the dispose method from Game
     */
    @Override
    public void dispose()
    {
        batch.dispose();
    }
}
