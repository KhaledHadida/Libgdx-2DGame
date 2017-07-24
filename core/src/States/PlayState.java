/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.zombies;

/**
 *
 * @author Hadida
 */
public class PlayState extends State{
    private Texture bg;
    private World world;
    
    private Box2DDebugRenderer b2dr;

    public PlayState(StateManager sm) {
        super(sm);
        
     
        
        world = new World(new Vector2(0, -9.81f),true);
        b2dr = new Box2DDebugRenderer();
        bg = new Texture("background.png");
        //create platform
        
        BodyDef def = new BodyDef();
        def.position.set(zombies.WIDTH/2,zombies.HEIGHT/2);
        def.type = BodyType.StaticBody;
        
        //create body into world
        Body body = world.createBody(def);
        
        PolygonShape shape = new PolygonShape();
        //set size, it is doubled what you put.
        shape.setAsBox(50, 5);
        //create fixture so that we can see the body
        FixtureDef fdef = new FixtureDef();
        //assign the shape to it
        fdef.shape = shape;
        body.createFixture(fdef);
        
        // create falling box
		def.position.set(160 / zombies.PPM, 200 / zombies.PPM);
		def.type = BodyType.DynamicBody;
		body = world.createBody(def);
		
		shape.setAsBox(5 / zombies.PPM, 5 / zombies.PPM);
		fdef.shape = shape;
		body.createFixture(fdef);
		
        
       
        
        //Static body - dont move, unaffected by forces

        //kinematic body - don't get affected by forces. (moving platform) 
       //dynamic body - always get affected by forces (player)
        
        
//        bg = new Texture("background.png");
    }

    @Override
    public void render(SpriteBatch batch) {
      
      
      b2dr.render(world, getCombinedCamera());
      
        if(Gdx.input.isTouched()){
            System.out.println(getViewHeight());
        }
      
        
        

       batch.begin();

//         Draws the background on to the screen, and sets its size according to screen
        batch.draw(bg, 0, 0, zombies.WIDTH, zombies.HEIGHT);
        
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        world.step(deltaTime, 6, 2);
    }

    @Override
    public void handleInput() {
      
    }

    @Override
    public void dispose() {
    }
}
