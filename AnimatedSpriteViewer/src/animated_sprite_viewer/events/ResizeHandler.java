/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animated_sprite_viewer.events;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import sprite_renderer.SceneRenderer;
import sprite_renderer.Sprite;
import sprite_renderer.SpriteType;

/**
 * @author Michael Salerno 
 * ID# 108512298 
 *
 **/
class ResizeHandler implements ComponentListener {
    
    SceneRenderer renderer;             //so i can get how big the renderer is
    ArrayList<SpriteType> spriteTypes;  //so i can get the width and height of the sprites
    ArrayList<Sprite> sprites;          //so i can changed the position of the sprites in the renderer

    /**
     * a full constructor
     * @param renderer the renderer that will be drawing the sprites.
     * @param spriteTypes a list of all of the spriteTypes
     * @param sprites a list of the sprites that the renderer is looking at
     */
    public ResizeHandler(SceneRenderer renderer, ArrayList<SpriteType> spriteTypes, ArrayList<Sprite> sprites) {
        this.renderer = renderer;
        this.spriteTypes = spriteTypes;
        this.sprites = sprites;
    }

    /**
     * puts sprites in the middle in the screen when
     * the window gets resized.
     * @param ce a ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent ce) {     
        //if the sprite list is not empty then reposition the sprite in the center of the screen
        if(!sprites.isEmpty()){
            sprites.get(0).setPositionX((renderer.getWidth() / 2.0f ) - (spriteTypes.get(0).getWidth() / 2.0f));
            sprites.get(0).setPositionY((renderer.getHeight() / 2.0f ) - (spriteTypes.get(0).getHeight() / 2.0f));
        }
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
        //do nothing
    }

    @Override
    public void componentShown(ComponentEvent ce) {
        //do nothing
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
        //do nothing
    }

}
