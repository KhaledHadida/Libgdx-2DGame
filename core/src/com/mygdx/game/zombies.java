package com.mygdx.game;

import States.PlayState;
import States.State;
import States.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class zombies extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    //state manager to "manage" order of states
    private StateManager stateManager;
    //resolution of the screen
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    public static final float PPM = 100;
    
    private OrthographicCamera cam;
        
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
                
                stateManager = new StateManager();
                
        State firstScreen = new PlayState(stateManager); 
        //set the screen (load first screen)
        stateManager.push(firstScreen);
        
	}

	@Override
	public void render () {
	
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
                
        // handle input
        stateManager.handleInput();
        // update the game states
        stateManager.update(Gdx.graphics.getDeltaTime());
        // draw the screen
        stateManager.render(batch);
        
      
           
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
