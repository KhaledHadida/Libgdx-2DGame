/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Sprites.InteractiveObjects;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * is what gets called when 2fixtures collide with each other
 * @author Hadida
 */
public class WorldContactListener implements ContactListener {

    //when they begin to collide 
    @Override
    public void beginContact(Contact contact) {
       //grab fixtures  of logs doors etc
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        //check if head contacts any of above
        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            //does fixA == head? if yes fixA otherwise fixB
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            //does head = fixA? if yes fixB otherwise its fixA
            Fixture object = head == fixA ? fixB : fixA;
            
            //grabs all objects
            if(object.getUserData()instanceof InteractiveObjects){
                //execute  the method
                ((InteractiveObjects) object.getUserData()).onHeadHit();
            
        }
        }
        
        
    }

    
    //when they disconnect from each other
    @Override
    public void endContact(Contact contact) {
        System.out.println("end");
    }

    //change characteristics of the collision
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
    

    //What happens when it is touched 
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
    
}
