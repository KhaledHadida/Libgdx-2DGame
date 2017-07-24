/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.zombieGrind;

/**
 *
 * @author Hadida
 */
public abstract class InteractiveObjects {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    
    public InteractiveObjects(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        
                 //first start with body
        BodyDef bdef = new BodyDef();
        //fixture def
        FixtureDef fdef = new FixtureDef();
        //polygon
        PolygonShape shape = new PolygonShape();
        
          bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((bounds.getX() + bounds.getWidth() /2)/ zombieGrind.PPM , (bounds.getY() + bounds.getHeight()/2)/ zombieGrind.PPM);
            
            //add body to world
            body = world.createBody(bdef);
            
            shape.setAsBox((bounds.getWidth() /2) / zombieGrind.PPM , (bounds.getHeight() /2) / zombieGrind.PPM);
            fdef.shape = shape;
//            body.createFixture(fdef);
            fixture = body.createFixture(fdef);
            
        
    }
    
    public abstract void onHeadHit();
    
    
    
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
 
    }   
    
    
    //Grahpic layer through tmx
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        //get tile: e.g tile 16, located at 1600
        return layer.getCell((int)((body.getPosition().x* zombieGrind.PPM)/64), (int)((body.getPosition().y*zombieGrind.PPM)/64));
    }
    
    
}
