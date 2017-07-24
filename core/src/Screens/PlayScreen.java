/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;


import Scenes.Hud;
import Sprites.Player1;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.zombieGrind;
import java.util.ArrayList;

/**
 *
 * @author Hadida
 */
public class PlayScreen implements Screen{

    
    private TextureAtlas atlas;
  
  private Hud hud;
    private zombieGrind game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    
        //load in the map (one we customly made)
    private TmxMapLoader maploader;
    //reference map
    private TiledMap map;
    //render map
    private OrthogonalTiledMapRenderer renderer;
    
    
    //box2d variables
    private World world;
    //visual representation of our bodies
    private Box2DDebugRenderer b2dr;
    
    //define player
    private Player1 player;
    
      //light
    private RayHandler rayHandler;

    private PointLight myLight;
    private ArrayList<PointLight> lights;
    
    
    public PlayScreen(zombieGrind game){
        atlas = new TextureAtlas("characters.pack");
        this.game  = game;
        
       //The PPM is needed so that all the movement is smooth
        
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(zombieGrind.WIDTH / zombieGrind.PPM,zombieGrind.HEIGHT / zombieGrind.PPM, gamecam);
          //initialize our hud (stats pretty much)
        hud = new Hud(game.batch);
        
        //creates the map
        maploader = new TmxMapLoader();
        //load map
        map = maploader.load("zombies.tmx");
       
        renderer = new OrthogonalTiledMapRenderer(map , 1/ zombieGrind.PPM);
        //update pos of cam
        gamecam.position.set(0, 0,20);
        
        
        //initialize world (velocity) I chose 0,0 since its top view (sleep obj at rest.. true)
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        
        
        //initializing the world
        new B2WorldCreator(world,map);
    
        
        //initialize player
        player = new Player1(world, this);
        
    
        
        //to specify what blocks are being collided with (wall, door etc)
      world.setContactListener(new WorldContactListener());
      
//              initialize rayhandler (lights box2d)
        rayHandler = new RayHandler(world);
        //Scale is from 0-1 f where it dims or brightens screen ( 1 f being bright, 0 being dark)
        rayHandler.setAmbientLight(0.5f);
        
        // Initialize, param(rayhandler, amount of rays projected in circle, color of ray, radius,x,y)
        myLight = new PointLight(rayHandler,100,Color.BLACK, 300/zombieGrind.PPM, 0,0);
        myLight.attachToBody(player.getBody());
        //doesnt bleed into the body
        myLight.setSoftnessLength(0f);
        
    }
    
    public TextureAtlas getAtlas(){
        return atlas;
    }
    
    
    @Override
    public void show() {
    }
    
    public void handleInput(float dt){
        //check if right is pressed and if the speed is lower than 2
      if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2 )
          //linear impulse is instant movement (new vector.. first parameter is velocity, second is where you apply it, third will it awake object)
          player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(),true);
          
//         player.b2body.setLinearVelocity(3, 0);
          
      
      
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2  )
          //angular is instant movement (new vector.. first parameter is velocity, second is where you apply it, third will it awake object)
          player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(),true);
          
//           player.b2body.setLinearVelocity(-3, 0);
      
        
        //up
           if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 2 )
          //angular is instant movement (new vector.. first parameter is velocity, second is where you apply it, third will it awake object)
          player.b2body.applyLinearImpulse(new Vector2(0,0.1f), player.b2body.getWorldCenter(),true);
//           player.b2body.setLinearVelocity(0, 3);
      
           
             //DOWN
           if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= -2  )
          //angular is instant movement (new vector.. first parameter is velocity, second is where you apply it, third will it awake object)
          player.b2body.applyLinearImpulse(new Vector2(0,-0.1f), player.b2body.getWorldCenter(),true);
//          player.b2body.setLinearVelocity(new Vector2(0,0));
         
//           player.b2body.setLinearVelocity(0, -3);
      
           
           //stop player
           
     
     
    }

    public void update(float dt){
        handleInput(dt);
         
        world.step(1/60f, 6, 2);
       
        rayHandler.update();
        
        gamecam.update();
        hud.update(dt);
        //update camera tracking player
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;
       
         
        
        player.update(dt);
       
        //render what we can see
        renderer.setView(gamecam);
 
        rayHandler.setCombinedMatrix(gamecam);
        
        
        
    }
    
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderer.render();
        
        
        
        
        //show whats on the screen
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
        //to draw box2d
        b2dr.render(world, gamecam.combined);
        rayHandler.render();
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
       
        game.batch.end();
         
        
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
       
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        rayHandler.dispose();
    }
    
}

