/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.zombieGrind;

/**
 *
 * @author Hadida
 */
public class Logs extends InteractiveObjects{

    public Logs(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        
        fixture.setUserData(this);
        
        //set filter for specific object
        setCategoryFilter(zombieGrind.LOG_BIT);
    
        
        
    }

    @Override
    public void onHeadHit() {
        System.out.println("Logs!");
        setCategoryFilter(zombieGrind.DESTROYED_BIT);
        
  
        getCell().setTile(null);
        Hud.addScore(200);
    }
    
}
