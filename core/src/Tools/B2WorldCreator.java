/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import Sprites.Logs;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.zombieGrind;

/**
 *
 * @author Hadida
 */
public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
            BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        //Define the ground and set it 
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2)/ zombieGrind.PPM , (rect.getY() + rect.getHeight()/2)/ zombieGrind.PPM);
            
            //add body to world
            body = world.createBody(bdef);
            
            shape.setAsBox((rect.getWidth() /2) / zombieGrind.PPM , (rect.getHeight() /2) / zombieGrind.PPM);
       
//                 FixtureDef groundFixture = new FixtureDef();
//            groundFixture.density = 0.0f;
//            groundFixture.shape = shape;
//            groundFixture.restitution = .5f;
//            groundFixture.friction = 5f;
            
            fdef.shape = shape;
            body.createFixture(fdef);
            
            
        }
        
//            for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() /2)/ zombieGrind.PPM , (rect.getY() + rect.getHeight()/2)/ zombieGrind.PPM);
//            
//            //add body to world
//            body = world.createBody(bdef);
//            
////            shape.setAsBox((rect.getWidth() /2) / zombieGrind.PPM , (rect.getHeight() /2) / zombieGrind.PPM);
//       
//                 FixtureDef groundFixture = new FixtureDef();
//            groundFixture.density = 0.0f;
//            groundFixture.shape = shape;
//            groundFixture.restitution = .5f;
//            groundFixture.friction = 0f;
//            
////            fdef.shape = shape;
//            body.createFixture(groundFixture);
//            
//            
//        }
        
        
        //define logs
          for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            //calls logs method to define
            new Logs(world, map, rect);
            
            
        }
        
    }
}
