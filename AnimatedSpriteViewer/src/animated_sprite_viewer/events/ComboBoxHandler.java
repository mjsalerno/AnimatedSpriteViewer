package animated_sprite_viewer.events;

import animated_sprite_viewer.AnimatedSpriteViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import sprite_renderer.AnimationState;
import sprite_renderer.Sprite;

/**
 * handles all of the events thrown by the combo box.
 *
 * @author Michael
 */
public class ComboBoxHandler implements ActionListener {

    private JComboBox box;                 //to see what was selected
    private ArrayList<Sprite> sprites;     //to change the animation state

    /**
     * a full constructor.
     *
     * @param box the JComboBox that will throw events.
     * @param sprites an ArrayList that has the sprite the SceneRenderer is
     * drawing
     */
    public ComboBoxHandler(JComboBox box, ArrayList<Sprite> sprites) {
        this.box = box;
        this.sprites = sprites;
    }

    /**
     * changes the animation state of the Sprite to the one selected in the combo box
     * @param ae the ActionEvent that was thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (box != null && box.getItemAt(box.getSelectedIndex()) != null && !AnimatedSpriteViewer.SELECT_ANIMATION_TEXT.equals(box.getItemAt(box.getSelectedIndex()).toString())) {
            sprites.get(0).setAnimationState(AnimationState.valueOf(box.getItemAt(box.getSelectedIndex()).toString()));
        }

    }
}