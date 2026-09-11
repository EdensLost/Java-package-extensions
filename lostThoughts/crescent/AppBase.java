package lostThoughts.crescent;
import javax.imageio.ImageIO;
import javax.swing.*;

import lostThoughts.helpingHands.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


public class AppBase extends JFrame {

    // HEAD [Main Window Info]
    public int width = 500;
    public int height = 500;
    public JPanel windowPanel;
    public Point windowLocal = null;
    public int closeOperation = JFrame.DISPOSE_ON_CLOSE;
    public boolean isResizeable = false;

    public WindowListener closeListener = new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            System.out.println("App has been closed");
            System.exit(0);
        }
    };

    private double maxFramerate = 60;

    public String title = "";
    public String iconName = null;

    // HEAD [Scene info]
    public int curScene = 0;
    public SceneCG baseScene = new SceneCG("null scene");
    public ArrayList<SceneCG> appScenes = new ArrayList<>();
    
    // TITLE Main generation
    public AppBase() { }

    public void createApp() {
        Image appIcon = null;

        //*
        try {
            appIcon = ImageIO.read(getClass().getResource(this.iconName));
        } catch (Exception e) {
            PyJav.printl("Icon not presented or does not exist");
        }

        // Set the icon image
        if (appIcon != null) {
            setIconImage(appIcon);
        }
        //*/

        setTitle(this.title);
        setSize(this.width, this.height);
        setDefaultCloseOperation(this.closeOperation);

        // If no location is given the window will be placed in the middle of the screen
        if (this.windowLocal == null) {
            this.windowLocal = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            this.windowLocal.translate(width / -2, (height / -2) - 16);
            setLocation(this.windowLocal);
        }
        else {
            setLocation(this.windowLocal);
        }

        setResizable(this.isResizeable);

        // TITLE [Close operation]
        addWindowListener(this.closeListener);

        // Create a panel to draw and move the shape
        this.windowPanel = new JPanel(true) {
        
            @Override
            protected void paintComponent(Graphics mainGraphics) {
                // ***Sets up the paint component***
                super.paintComponent(mainGraphics);
                //

                // ***Makes the Graphics2D and turns on antialiasing***
                Graphics2D g2d = (Graphics2D) mainGraphics;

                RenderingHints renderHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setRenderingHints(renderHints);
                //

                // Generates the current scene
                if (appScenes.size() > 0) {
                    appScenes.get(curScene).generateScene(g2d);
                }
                else {
                    baseScene.generateScene(g2d);
                    
                }

                // Replays the loop (Repaints)
                if (true) {
                    UtilH.uWait(1000 / maxFramerate);
                    repaint();
                }
                //

            
            }
        };
        
        if (appScenes.size() > 0) {
            windowPanel.addMouseListener(appScenes.get(curScene).sceneMouseL);
            windowPanel.addKeyListener(appScenes.get(curScene).sceneKeyL);
        }
        else {
            windowPanel.addMouseListener(baseScene.sceneMouseL);
            windowPanel.addKeyListener(baseScene.sceneKeyL);
        }

        windowPanel.setBackground(Color.black);
        windowPanel.setPreferredSize(new Dimension(getSize().width, getSize().height));
        
        windowPanel.setFocusable(true);
        windowPanel.requestFocusInWindow();

        for (SceneCG scene : appScenes) {
            scene.setWindow(windowPanel);
        }

        baseScene.setWindow(windowPanel);

        add(windowPanel);
        pack();
    }

    // TITLE Sets
    /**
     * Sets the dimensions of the app and resizes the window
     * 
     * @param nWidth = The width of the window
     * @param nHeight = The height of the window
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setDim(int nWidth, int nHeight) {
        this.width = nWidth;
        this.height = nHeight;
        
        setSize(this.width, this.height);

        return this;
    }

    /**
     * Sets the title of the window
     * 
     * @param nTitle = The new title of the window
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setText(String nTitle) {
        this.title = nTitle;

        setTitle(this.title);

        return this;
    }

    /**
     * Sets the icon of the window
     * 
     * @param nIconName = The img name (and file type [ie: .png]) as a string
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setIcon(String nIconName) {
        this.iconName = nIconName;

        Image appIcon = null;

        try {
            appIcon = ImageIO.read(getClass().getResource(this.iconName));
        } catch (Exception e) {
            
        }

        // Set the icon image
        if (appIcon != null) {
            setIconImage(appIcon);
        }

        return this;
    }

    /**
     * Sets the maximum maxFramerate of the window
     * 
     * @param nFramerate = The new maximum maxFramerate the window is allowed to repaint itself
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setFramerate(int nFramerate) {
        this.maxFramerate = nFramerate;

        return this;
    }

    /**
     * Sets the location of the window
     * 
     * @param nlocation = The new location of the window
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setLocal(Point nlocation) {
        nlocation.setLocation(nlocation.x - 8, nlocation.y);
        this.windowLocal = nlocation;

        setLocation(this.windowLocal);
        return this;
    }

    /**
     * Sets the location of the window
     * 
     * @param nCloseType = The way the window is handled on close {@code Refrence: JFrame}
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setCloseOperation(int nCloseType) {
        this.closeOperation = nCloseType;

        setDefaultCloseOperation(this.closeOperation);
        return this;
    }

    /**
     * Sets if the window can be resized or not
     * 
     * @param nResizeable = If the window is allowed to be resized or not
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setResizability(boolean nResizeable) {
        this.isResizeable = nResizeable;

        setResizable(this.isResizeable);

        return this;
    }

    /**
     * Sets the function that is run when the app is closed
     * 
     * @param nListener = The WindowAdapter that overrides the windowClosed(WindowEvent e) function
     * 
     * @example new WindowAdapter() {
            //@Override
            public void windowClosed(WindowEvent e) {
                System.out.println("App has been closed.");
                System.exit(0);
            }
        }
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setClosingFunct(WindowListener nListener) {
        removeWindowListener(closeListener);

        this.closeListener = nListener;

        addWindowListener(this.closeListener);

        return this;
    }

    /**
     * Sets the scenes that the app can use
     * 
     * @param nScenes = The new ArrayList of scenes for the app
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setScenes(ArrayList<SceneCG> nScenes) {
        this.appScenes = nScenes;

        return this;
    }

    /**
     * Sets the scenes that the app can use (window should be present when setting the scene)
     * 
     * @param nScenes = The new ArrayList of scenes for the app
     * @param setScene = The scene to set the app to in terms of its index
     * 
     * @return {@code AppExample} = Returns the object
     */
    public AppBase setScenes(ArrayList<SceneCG> nScenes, int setScene) {
        this.appScenes = nScenes;

        updateScene(setScene);

        return this;
    }

    // TITLE Methods
    /**
     * Updates the current app scene removing key and mouse listeners of the previous one and adds the new key and mouse listeners
     * 
     * @param newScene = The number corresponding to the scene you want the app changed to
     * 
     */
    public void updateScene(int newScene) {
        try {
            windowPanel.removeMouseListener(appScenes.get(curScene).sceneMouseL);
            windowPanel.removeKeyListener(appScenes.get(curScene).sceneKeyL);
        } catch (Exception e) {
            PyJav.printl("Window not generated");
        }
        
        curScene = newScene;

        try {
            windowPanel.addMouseListener(appScenes.get(curScene).sceneMouseL);
            windowPanel.addKeyListener(appScenes.get(curScene).sceneKeyL);
        } catch (Exception e) {
            PyJav.printl("Window not generated");
        }
    }

    /**
     * Adds a new scene to the list of scenes the app has
     * 
     * @param nScene = The scene object that you wan to add to the app scenes
     * 
     */
    public void addScene(SceneCG nScene) {
        appScenes.add(nScene);
    }

    /**
     * Removes a given scene from the app's scene list
     * 
     * @param nScene = The scene object that you wan to remove from the app scenes
     * 
     */
    public void removeScene(SceneCG rScene) {
        appScenes.remove(rScene);
    }

    public void genApp() {

        SwingUtilities.invokeLater(() -> {
            AppBase window = this;
            this.createApp();
            window.setVisible(true);
            
        });
        
    }

}
