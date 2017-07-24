/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Screens.PlayScreen;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.zombieGrind;

/**
 *
 * @author Hadida
 */
public class Player1 extends Sprite {

    public enum State {

        STANDING, RUNNING, RUNNINGUP, RUNNINGDOWN
    };
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;
    //texture for character
    private TextureRegion playerStanding;
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerRunUp;
    private Animation<TextureRegion> playerRunDown;
    private float stateTimer;
    private boolean right;
    

  
    
    public Player1(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("SpriteSheet"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        right = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        definePlayer();
        for (int i = 0; i < 3; i++) //right
        {
            frames.add(new TextureRegion(getTexture(), i * 180, 240, 180, 240));
        }
        playerRun = new Animation<TextureRegion>(0.2f, frames);

        frames.clear();
        //left
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), i * 180, 770, 180, 240));
        }
        playerRunUp = new Animation<TextureRegion>(0.2f, frames);

        frames.clear();
        //down
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), i * 180, 0, 180, 240));
        }
        playerRunDown = new Animation<TextureRegion>(0.2f, frames);

        playerStanding = new TextureRegion(getTexture(), 180, 0, 180, 240);
        setBounds(0, 0, 50 / zombieGrind.PPM, 50 / zombieGrind.PPM);
        setRegion(playerStanding);
        
        
        

    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(50 / zombieGrind.PPM, 50 / zombieGrind.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
//        shape.setRadius(40/ zombieGrind.PPM);
        shape.setAsBox(15 / zombieGrind.PPM, 15 / zombieGrind.PPM);

        //filter assigning bit
        fdef.filter.categoryBits = zombieGrind.CHARACTER_BIT;
        //what can your character collide with?
        fdef.filter.maskBits = zombieGrind.DEFAULT_BIT | zombieGrind.LOG_BIT;
        
        fdef.shape = shape;
        b2body.createFixture(fdef);

//        EdgeShape feet = new EdgeShape();
//        feet.set(new Vector2(-2 / zombieGrind.PPM, -8 / zombieGrind.PPM), new Vector2(2 / zombieGrind.PPM, -8 / zombieGrind.PPM));
//        fdef.shape = feet;
//        fdef.isSensor = false;
//        b2body.createFixture(fdef);

//        EdgeShape head = new EdgeShape();
//        //it sets where the head is located
//        head.set(new Vector2(-2/zombieGrind.PPM, 14.5f/zombieGrind.PPM), new Vector2(2/zombieGrind.PPM, 14.5f/zombieGrind.PPM));
//        fdef.shape = head;
//        fdef.isSensor = true;
        
        b2body.createFixture(fdef).setUserData("head");
        
        
        
   
        
        
        
    }

    public void update(float dt) {
        
        
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //sets animation

        setRegion(getFrame(dt));
        

   

        
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
      
        TextureRegion region;
        switch (currentState) {
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);

                break;

            case RUNNINGUP:
                region = playerRunUp.getKeyFrame(stateTimer, true);

                break;

            case RUNNINGDOWN:
                region = playerRunDown.getKeyFrame(stateTimer, true);

                break;
            case STANDING:
            default:
                region = playerStanding;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !right) && !region.isFlipX()) {
            //First parameter is X, second is Y    
            region.flip(true, false);
            right = false;
        } else if ((b2body.getLinearVelocity().x > 0 || right) && region.isFlipX()) {
            region.flip(true, false);
            right = true;
        }
//down
        if (b2body.getLinearVelocity().y < 0) {

        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;

    }

    public State getState() {
        if (b2body.getLinearVelocity().x != 0 && b2body.getLinearVelocity().y == 0) {

            return State.RUNNING;

        } else if (b2body.getLinearVelocity().y > 0) {

            return State.RUNNINGUP;
        } else if (b2body.getLinearVelocity().y < 0) {

            return State.RUNNINGDOWN;
        } else {
            return State.STANDING;
        }

    }
    
    
    public Body getBody(){
        return this.b2body;
    }
  
}
