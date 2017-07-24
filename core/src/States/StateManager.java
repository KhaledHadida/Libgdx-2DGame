package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;


/**
 *
 * @author hadik9595
 */
public class StateManager {
    private Stack<State> states;
    
    public StateManager(){
        states = new Stack<State>();
    }
    
    public void push(State s){
        states.push(s);
    }
    
    public void pop(){
        State s = states.pop();
        s.dispose();
    }
    
    public void set(State s){
        pop();
        push(s);
    }
    
    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }
    
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }

    public void handleInput() {
        states.peek().handleInput();
    }

    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }
}