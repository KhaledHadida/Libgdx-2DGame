package com.mygdx.game;

import Screens.PlayScreen;
import States.PlayState;
import States.State;
import States.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class zombieGrind extends Game {
	public SpriteBatch batch;
	Texture img;

    //state manager to "manage" order of states
    private StateManager stateManager;
    //resolution of the screen
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    public static final float PPM = 100;
    public static final short DEFAULT_BIT = 1;
    public static final short CHARACTER_BIT = 2;
    public static final short LOG_BIT = 4;
    public static final short DESTROYED_BIT = 8;
    
    private OrthographicCamera cam;
        
	
	@Override
	public void create () {
		batch = new SpriteBatch();
                setScreen(new PlayScreen(this));
		
      
        
	}

	@Override
	public void render () {
	
		Gdx.gl.glClearColor(1,0,0,1);
		super.render();
                
      
                
           
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
