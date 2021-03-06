package animated_sprite_viewer.events;

import animated_sprite_viewer.AnimatedSpriteViewer;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sprite_renderer.SceneRenderer;
import sprite_renderer.Sprite;
import sprite_renderer.SpriteType;

/**
 * Handles all of the events that are thrown by the JList
 *
 * @author Michael
 */
public class ListHandler implements ListSelectionListener {

    private ArrayList<String>[] names;          //so i know what the names of the actions are
    private JComboBox box;                      //so i can populate it with the action state names
    private JList jList;                        //to see that sprite was selected
    private ArrayList<Sprite> allSprites;       //all of the sprites premade so i can load them into the renderer
    private ArrayList<Sprite> sprites;          //the list of sprites the renderer is looking at
    private ArrayList<SpriteType> spriteTypes;  //all of the sprite types so i can see how big the images are
    private SceneRenderer renderer;             //so i can get the width of the rendering panel

    /**
     * a full constructor
     * @param names names of all of the animation states per sprite
     * @param allSprites all sprites that exist
     * @param sprites the sprite list that the renderer has
     * @param box the JComboBox
     * @param jList the JList
     * @param spriteTypes a list of all of the sprite types
     * @param renderer the renderer that will be rendering the sprites.
     */
    public ListHandler(ArrayList<String>[] names, ArrayList<Sprite> allSprites, ArrayList<Sprite> sprites, JComboBox box, JList jList, ArrayList<SpriteType> spriteTypes, SceneRenderer renderer) {
        this.names = names;
        this.box = box;
        this.jList = jList;
        this.allSprites = allSprites;
        this.sprites = sprites;
        this.spriteTypes = spriteTypes;
        this.renderer = renderer;
    }

    /**
     * Here's the actual method called when the user clicks the start animation
     * method, which results in unpausing of the renderer, and thus the animator
     * as well.
     *
     * @param ae Contains information about the event.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            renderer.unpauseScene();
            if (box != null && box.getItemCount() > 0) {
                renderer.setTimeScaler(1);  //reset the speed of the sprite once a new sprite was selected
                sprites.clear();
                sprites.add(allSprites.get(jList.getSelectedIndex())); //add the sprite that was selected

                //when a new sprite is selected, change the position of the sprite to the center of the screen
                allSprites.get(jList.getSelectedIndex()).setPositionX((renderer.getWidth() / 2.0f) - (spriteTypes.get(jList.getSelectedIndex()).getWidth() / 2.0f));
                allSprites.get(jList.getSelectedIndex()).setPositionY((renderer.getHeight() / 2.0f) - (spriteTypes.get(jList.getSelectedIndex()).getHeight() / 2.0f));

                //remove all of the old animationStates and add the new ones
                box.removeAllItems();
                box.addItem(AnimatedSpriteViewer.SELECT_ANIMATION_TEXT);
                for (String s : names[jList.getSelectedIndex()]) {
                    box.addItem(s);
                }
                box.setEnabled(true);
            }
        }
    }
}