package animated_sprite_viewer.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sprite_renderer.SceneRenderer;

/**
 * The StartAnimationHandler class responds to when the user
 * requests to speed up the animation.
 * 
 * @author  Michael
 */
public class SpeedUpAnimationHandler implements ActionListener
{
    // THIS IS REALLY THE ONLY ONE WHO CAN SPEED UP THE ANIMATION
    private SceneRenderer renderer;
    
    /**
     * Constructor will need the renderer for when the event happens.
     * 
     * @param initRenderer Renderers can speed up the rendering.
     */
    public SpeedUpAnimationHandler(SceneRenderer initRenderer)
    {
        // KEEP THIS FOR LATER
        renderer = initRenderer;
    }    

    /**
     * Here's the actual method called when the user clicks the 
     * speed up animation button, which results in the
     * animation being sped up.
     * 
     * @param ae Contains information about the event.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        renderer.setTimeScaler(renderer.getTimeScaler() - 0.1f);
    }
}