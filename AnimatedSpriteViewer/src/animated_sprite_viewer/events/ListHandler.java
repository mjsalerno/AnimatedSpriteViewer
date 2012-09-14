package animated_sprite_viewer.events;

import animated_sprite_viewer.AnimatedSpriteViewer;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The StartAnimationHandler class responds to when the user
 * requests to start animation.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 * @version 1.0
 */
public class ListHandler implements ListSelectionListener
{
    // THIS IS REALLY THE ONLY ONE WHO CAN PAUSE OR UNPAUSE ANIMATION
    private ArrayList<String>[] names;
    private JComboBox box;
    private JList jList;
    
    
   
    public ListHandler(ArrayList<String>[] names, JComboBox box, JList jList)
    {
        this.names = names;
        this.box = box;
        this.jList = jList;
    }    

    /**
     * Here's the actual method called when the user clicks the 
     * start animation method, which results in unpausing of the
     * renderer, and thus the animator as well.
     * 
     * @param ae Contains information about the event.
     */
   

    @Override
    public void valueChanged(ListSelectionEvent e) {        
        if (box != null && box.getItemCount() > 0) {  
            box.removeAllItems();
            box.addItem(AnimatedSpriteViewer.SELECT_ANIMATION_TEXT);
            for(String s : names[jList.getSelectedIndex()]){
                box.addItem(s);
            }
            box.setEnabled(true);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}