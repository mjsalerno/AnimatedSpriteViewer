package animated_sprite_viewer.events;

import animated_sprite_viewer.AnimatedSpriteViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import sprite_renderer.AnimationState;
import sprite_renderer.Sprite;

/**
 * The StartAnimationHandler class responds to when the user
 * requests to start animation.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 * @version 1.0
 */
public class ComboBoxHandler implements ActionListener
{
    private JComboBox box;
    private ArrayList<Sprite> sprites;
    
    
    public ComboBoxHandler(JComboBox box, ArrayList<Sprite> sprites)
    {
        this.box = box;
        this.sprites = sprites;
    }    

        @Override
    public void actionPerformed(ActionEvent ae) {
        if (box != null && box.getItemAt(box.getSelectedIndex()) != null &&!AnimatedSpriteViewer.SELECT_ANIMATION_TEXT.equals(box.getItemAt(box.getSelectedIndex()).toString())) {
            sprites.get(0).setAnimationState(AnimationState.valueOf(box.getItemAt(box.getSelectedIndex()).toString()));
        }

    }
}