/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author hadik9595
 */
public abstract class State {
    
    
    private OrthographicCamera cam;

    private StateManager stateManager;
    
    
    public abstract void render(SpriteBatch batch);
    public abstract void update(float deltaTime);
    public abstract void handleInput();
    public abstract void dispose();
    
    public State(StateManager sm){
        stateManager = sm;
        cam = new OrthographicCamera();
        
    }
    
    
    public StateManager getStateManager(){
        return stateManager;
    }
    
    public void setCameraView(float width, float height) {
        cam.setToOrtho(false, width, height);
        cam.update();
    }
    
    public void setCameraPosition(float x, float y) {
        cam.position.x = x;
        cam.position.y = y;
        cam.update();
    }
    
    public Matrix4 getCombinedCamera() {
        return cam.combined;
    }
    
    public void moveCameraX(float x) {
        cam.position.x = x;
        cam.update();
    }
    
    public float getCameraX() {
        return cam.position.x;
    }

    public float getCameraY() {
        return cam.position.y;
    }

    public float getViewWidth() {
    
        return cam.viewportWidth;
       
    }

    public float getViewHeight() {
        return cam.viewportHeight;
    }

    public void unproject(Vector3 touch) {
        cam.unproject(touch);
        
    }
    
    public void resize(int width, int height){
        cam.setToOrtho(false, width, height);
        cam.update();
    }
    
    
    
}
