package animated_sprite_viewer.events;

import animated_sprite_viewer.AnimatedSpriteViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
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
    private ArrayList<Sprite> allSprites;  //so i can add sprites back to the renderer
    private JList list;                    //so i can tell what sprite was selected

    /**
     * a full constructor.
     *
     * @param box the JComboBox that will throw events.
     * @param sprites an ArrayList that has the sprite the SceneRenderer is
     * drawing
     */
    public ComboBoxHandler(JComboBox box, ArrayList<Sprite> sprites, ArrayList<Sprite> allSprites, JList list) {
        this.box = box;
        this.sprites = sprites;
        this.allSprites = allSprites;
        this.list = list;
    }

    /**
     * changes the animation state of the Sprite to the one selected in the
     * combo box
     *
     * @param ae the ActionEvent that was thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //if the box exists and the item that was selected also exists...
        if (box != null && box.getItemAt(box.getSelectedIndex()) != null) {
            //if the box has seleted the default text remove the sprite from the renderer.
            if (AnimatedSpriteViewer.SELECT_ANIMATION_TEXT.equals(box.getItemAt(box.getSelectedIndex()).toString())) {
                sprites.clear();
            } else if (sprites.isEmpty()) {  //else if the spriteList is empty add the sprite to it
                sprites.add(allSprites.get(list.getSelectedIndex()));
            }
            //if the default text is not selected then change the animationstate.
            if (!AnimatedSpriteViewer.SELECT_ANIMATION_TEXT.equals(box.getItemAt(box.getSelectedIndex()).toString())) {
                sprites.get(0).setAnimationState(AnimationState.valueOf(box.getItemAt(box.getSelectedIndex()).toString()));
            }

        }

    }
}