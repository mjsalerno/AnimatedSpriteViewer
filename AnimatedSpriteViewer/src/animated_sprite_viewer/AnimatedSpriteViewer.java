package animated_sprite_viewer;

import animated_sprite_viewer.events.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.Border;
import sprite_renderer.AnimationState;
import sprite_renderer.SceneRenderer;
import sprite_renderer.Sprite;
import sprite_renderer.SpriteType;

/**
 * The AnimatedSpriteViewer application lets one load and view
 * sprite states and then view their animation. Note that all
 * data concerning the sprites should be done via .xml files.
 * 
 * The sprite types should be listed in the following file:
 * ./data/sprite_types/sprite_types_list.xml, 
 * 
 * which can be validated by:
 * ./data/sprite_types/sprite_types_list.xsd
 * 
 * Each sprite type then has its own description found inside
 * its own directory. They can be validated by:
 * ./data/sprite_types/sprite_type.xsd
 * 
 * @author  Richard McKenna &
 *          Debugging Enterprises
 * @version 1.0
 */
public class AnimatedSpriteViewer extends JFrame
{
    // FOR THE TITLE BAR
    public static final String APP_TITLE = "Animated Sprite Viewer";
    
    // FOR LOCATING ASSETS
    public static final String GUI_IMAGES_PATH = "./data/buttons/";
    public static final String SPRITES_DATA_PATH = "./data/sprite_types/";
    public static final String SPRITE_TYPE_LIST_FILE = "sprite_type_list.xml";
    public static final String SPRITE_TYPE_LIST_SCHEMA_FILE = "sprite_type_list.xsd";
    public static final String SPRITE_TYPE_SCHEMA_FILE = "sprite_type.xsd";
    public static final String SELECT_SPRITE_TYPE_TEXT = "Select Sprite Type";
    public static final String SELECT_ANIMATION_TEXT = "Select Animation State";
 
    // WE'LL ONLY ACTUALLY HAVE ONE SPRITE AT A TIME IN HERE,
    // THE ONE THAT WE ARE CURRENTLY VIEWING
    private ArrayList<Sprite> spriteList;
    
    // WE'LL LOAD ALL THE SPRITE TYPES INTO LIST
    // FROM AN XML FILE
    private ArrayList<String> spriteTypeNames;

    // THIS WILL DO OUR XML FILE LOADING FOR US
    private AnimatedSpriteXMLLoader xmlLoader;

    // THE WEST WILL PROVIDE SPRITE TYPE AND ANIM STATE SELECTION CONTROLS
    private JPanel westOfSouthPanel;

    // THIS WILL STORE A SELECTABLE LIST OF THE LOADED SPRITES
    private JScrollPane spriteTypesListJSP;
    private JList spriteTypesList;
    private DefaultListModel spriteTypesListModel;
    
    // THIS WELL LET THE USER CHOOSE DIFFERENT ANIMATION STATES TO VIEW
    private JComboBox spriteStateCombobox;
    private DefaultComboBoxModel spriteStateComboBoxModel;

    // THIS PANEL WILL ORGANIZE THE CENTER
    private JPanel southPanel;
    
    // THIS PANEL WILL RENDER OUR SPRITE
    private SceneRenderer sceneRenderingPanel;

    // THIS TOOLBAR WILL ALLOW THE USER TO CONTROL ANIMATION
    private JPanel animationToolbar;
    private JButton startButton;
    
    /**
     * The entire application will be initialized from here, including
     * the loading of all the sprite states from the xml file.
     */
    public AnimatedSpriteViewer()
    {
        initWindow();
        initData();
        initGUI();
        initHandlers();
    }
    
    /**
     * Sets up the window for use.
     */
    private void initWindow()
    {
        setTitle(APP_TITLE);
        setSize(700, 500);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Loads all the data needed for the sprite types. Note that we'll
     * need all the sprite states, including their art and animations,
     * loaded before we initialize the GUI.
     */
    private void initData()
    {
        // WE'LL ONLY PUT ONE SPRITE IN THIS
        spriteList = new ArrayList<Sprite>();
        
        // WE'LL PUT ALL THE SPRITE TYPES HERE
        spriteTypeNames = new ArrayList<String>();
        
        // LOAD THE SPRITE TYPES FROM THE XML FILE
        try
        {
            // THIS WILL LOAD AND VALIDATE
            // OUR XML FILES
            xmlLoader = new AnimatedSpriteXMLLoader(this);
            
            // FIRST UP IS THE SPRITE TYPES LIST
            xmlLoader.loadSpriteTypeNames(SPRITES_DATA_PATH,
                             SPRITE_TYPE_LIST_FILE, spriteTypeNames);           
        }
        catch(InvalidXMLFileFormatException ixffe)
        {
            // IF WE DON'T HAVE A VALID SPRITE TYPE 
            // LIST WE HAVE NOTHING TO DO, WE'LL POP
            // OPEN A DIALOG BOX SO THE USER KNOWS
            // WHAT HAPPENED
            JOptionPane.showMessageDialog(this, ixffe.toString());
            System.exit(0);
        }
    }
    
    /**
     * This initializes all the GUI components and places
     * them into the frame in their appropriate locations.
     */
    private void initGUI()
    {
        // NOTE THAT WE'VE ALREADY LOADED THE XML FILE
        // WITH ALL THE SPRITE TYPES, SO WE CAN USE
        // THEM HERE TO POPULATE THE JList
        spriteTypesListModel = new DefaultListModel();
        Iterator<String> spriteTypeNamesIt = spriteTypeNames.iterator();
        while (spriteTypeNamesIt.hasNext())
        {
            String spriteTypeName = spriteTypeNamesIt.next();
            spriteTypesListModel.addElement(spriteTypeName);
        }
        spriteTypesList = new JList();
        spriteTypesList.setModel(spriteTypesListModel);
        spriteTypesListJSP = new JScrollPane(spriteTypesList);
              
        // OUR COMBO BOX STARTS OUT EMPTY
        spriteStateComboBoxModel = new DefaultComboBoxModel();        
        spriteStateCombobox = new JComboBox();
        spriteStateCombobox.setModel(spriteStateComboBoxModel);
        clearAnimationStatesComboBox();

        // NOW LET'S ARRANGE ALL OUR CONTROLS IN THE WEST
        westOfSouthPanel = new JPanel();
        westOfSouthPanel.setLayout(new BorderLayout());
        westOfSouthPanel.add(spriteTypesList, BorderLayout.NORTH);
        westOfSouthPanel.add(spriteStateCombobox, BorderLayout.SOUTH);
        
        // AND LET'S PUT A TITLED BORDER AROUND THE WEST OF THE SOUTH
        Border etchedBorder = BorderFactory.createEtchedBorder();
        Border titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Sprite Type Selection");
        westOfSouthPanel.setBorder(titledBorder);       

        // NOW THE STUFF FOR THE SOUTH
        animationToolbar = new JPanel();         
        MediaTracker mt = new MediaTracker(this);
        startButton = initButton(   "StartAnimationButton.png",     "Start Animation",      mt, 0, animationToolbar);
        try { mt.waitForAll(); }
        catch(InterruptedException ie)
        { ie.printStackTrace(); }

        // LET'S PUT OUR STUFF IN THE SOUTH
        southPanel = new JPanel();
        southPanel.add(westOfSouthPanel);
        southPanel.add(animationToolbar);
        
        // AND OF COURSE OUR RENDERING PANEL
        sceneRenderingPanel = new SceneRenderer(spriteList);
        sceneRenderingPanel.setBackground(Color.white);
        sceneRenderingPanel.startScene();
        
        // AND LET'S ARRANGE EVERYTHING IN THE FRAME
        add(sceneRenderingPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This helper method empties the combo box with animations
     * and disables the component.
     */
    private void clearAnimationStatesComboBox()
    {
        spriteStateComboBoxModel.removeAllElements();
        spriteStateComboBoxModel.addElement(SELECT_ANIMATION_TEXT);        
        spriteStateCombobox.setEnabled(false);      
    }
  
    /**
     * This is a helper method for making a button. It loads the image and sets
     * it as the button image. It then puts it in the panel.
     * 
     * @param iconFilename Image for button
     * @param tooltip Tooltip for button
     * @param mt Used for batch loading of images
     * @param id Numeric id of image to help with batch loading
     * @param panel The container to place the button into
     * 
     * @return The fully constructed button, ready for use.
     */
    private JButton initButton(String iconFilename, String tooltip, MediaTracker mt, int id, JPanel panel)
    {
        // LOAD THE IMAGE
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(GUI_IMAGES_PATH + iconFilename);
        mt.addImage(img, id);
        
        // AND USE IT TO BUILD THE BUTTON
        ImageIcon ii = new ImageIcon(img);
        JButton button = new JButton(ii);
        button.setToolTipText(tooltip);
        
        // LET'S PUT A LITTLE BUFFER AROUND THE IMAGE AND THE EDGE OF THE BUTTON
        Insets insets = new Insets(2,2,2,2);
        button.setMargin(insets);
        
        // PUT THE BUTTON IN THE CONTAINER
        panel.add(button);
        
        // AND SEND THE CONSTRUCTED BUTTON BACK
        return button;
    }
    
    /**
     * This helper links up all the components with their event
     * handlers, ensuring the proper responses.
     */
    private void initHandlers()
    {
        // CONSTRUCT AND REGISTER ALL THE HANDLERS
        StartAnimationHandler sah = new StartAnimationHandler(sceneRenderingPanel);
        startButton.addActionListener(sah);
    }
    
    /**
     * This is where this app starts. The main method just constructs
     * the frame and then sets it visible, handing off control to Swing.
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        // START IT UP
        AnimatedSpriteViewer appWindow = new AnimatedSpriteViewer();
        appWindow.setVisible(true);
    }
}