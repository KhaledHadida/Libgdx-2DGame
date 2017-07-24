/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.zombieGrind;

/**
 *
 * @author Hadida
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    
    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    
    Label countdownLabel;
    static Label scoreLabel;
    Label timeLabel;
    

    
    public Hud(SpriteBatch sb){
        //initialize
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        
        //specify viewport 
        viewport = new FitViewport(zombieGrind.WIDTH, zombieGrind.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);
        
        Table table = new Table();
        //put it at top of stage
        table.top();
        //table is size of our stage
        table.setFillParent(true);
        
        //initializing countdown label 
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(),Color.RED));
        
        //now add them
        table.add(timeLabel);
        table.row();
        table.add(countdownLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
        
    
    }
    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
    
}
