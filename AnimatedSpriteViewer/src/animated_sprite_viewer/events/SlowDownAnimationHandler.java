package animated_sprite_viewer.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sprite_renderer.SceneRenderer;

/**
 * slows down the sprite when the 
 * @author roofis0
 */
public class SlowDownAnimationHandler implements ActionListener
{
    // THIS IS REALLY THE ONLY ONE WHO CAN SLOW DOWN THE ANIMATION
    private SceneRenderer renderer;
    
    /**
     * Constructor will need the renderer for when the event happens.
     * 
     * @param initRenderer Renderers can slow down the rendering.
     */
    public SlowDownAnimationHandler(SceneRenderer initRenderer)
    {
        // KEEP THIS FOR LATER
        renderer = initRenderer;
    }    

    /**
     * Here's the actual method called when the user clicks the 
     * slow animation method, which results in the slowing down of the
     * renderer, and thus the animator as well.
     * 
     * @param ae Contains information about the event.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        renderer.setTimeScaler(renderer.getTimeScaler() + 0.1f);
    }
}