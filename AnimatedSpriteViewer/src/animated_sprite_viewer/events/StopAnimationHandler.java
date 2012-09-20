package animated_sprite_viewer.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sprite_renderer.SceneRenderer;

/**
 * Stops the animation when the button is pressed
 * @author roofis0
 */
public class StopAnimationHandler implements ActionListener
{
    // THIS IS REALLY THE ONLY ONE WHO CAN STOP ANIMATION
    private SceneRenderer renderer;
    
    /**
     * Constructor will need the renderer for when the event happens.
     * 
     * @param initRenderer Renderers stop the rendering.
     */
    public StopAnimationHandler(SceneRenderer initRenderer)
    {
        // KEEP THIS FOR LATER
        renderer = initRenderer;
    }    

    /**
     * Here's the actual method called when the user clicks the 
     * stop animation button, which results in the stopping of the
     * renderer, and thus the animator as well.
     * 
     * @param ae Contains information about the event.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        renderer.pauseScene();
    }
}