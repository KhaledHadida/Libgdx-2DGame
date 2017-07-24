/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Hadida
 */
public class Booster extends InteractiveObjects {
    public Booster(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        
        //first start with body
        BodyDef bdef = new BodyDef();
        //fixture def
        FixtureDef fdef = new FixtureDef();
        //polygon
        PolygonShape shape = new PolygonShape();
        
        //be able to use fixture
        fixture.setUserData(this);
       
        
    }

    @Override
    public void onHeadHit() {
         System.out.println("BOOST");
    }
    
}
