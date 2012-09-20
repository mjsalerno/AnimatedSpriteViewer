/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animated_sprite_viewer;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import sprite_renderer.SceneRenderer;
import sprite_renderer.Sprite;
import sprite_renderer.SpriteType;

/**
 * @author Michael Salerno 
 * ID# 108512298 
 * E-Mail roofis20002003@yahoo.com
 * Homework2 
 * CSE214 
 * Recitation 04 Phillip Huff 
 * Grading TA Ming Chen
 *
 **/
class ResizeHandler implements ComponentListener {
    
    SceneRenderer renderer;
    ArrayList<SpriteType> spriteTypes;
    ArrayList<Sprite> sprites;

    public ResizeHandler(SceneRenderer renderer, ArrayList<SpriteType> spriteTypes, ArrayList<Sprite> sprites) {
        this.renderer = renderer;
        this.spriteTypes = spriteTypes;
        this.sprites = sprites;
    }

    @Override
    public void componentResized(ComponentEvent ce) {     
        if(!sprites.isEmpty()){
            sprites.get(0).setPositionX((renderer.getWidth() / 2.0f ) - (spriteTypes.get(0).getWidth() / 2.0f));
            sprites.get(0).setPositionY((renderer.getHeight() / 2.0f ) - (spriteTypes.get(0).getHeight() / 2.0f));
        }
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }

}
