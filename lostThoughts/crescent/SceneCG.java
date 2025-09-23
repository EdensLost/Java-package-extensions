package lostThoughts.crescent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

import lostThoughts.helpingHands.PyJav;

public class SceneCG {
    /**Will run whatever is inside before drawing the scene*/
    public ActionInterfaceCG preSceneFunction;

    /**Will run whatever is inside after drawing the scene*/
    public ActionInterfaceCG postSceneFunction;

    /**Will check for if a key is released */
    public KeyInterfaceCG keyReleasedFunction;
    public interface KeyInterfaceCG {
        void apply(KeyEvent e);
    }

    public Graphics2D g2d;

    public String sceneName;
    public Color backgroundColor;
    public JPanel sceneWindow;

    public MouseListener sceneMouseL;
    public ArrayList<ClickableGroupObjectCG> mouseClickables = new ArrayList<>();
    public KeyListener sceneKeyL;
    public ArrayList<TextBoxCG> textBoxes = new ArrayList<>();
    public ArrayList<BaseObjectCG> sceneObjects = new ArrayList<>();

    public SceneCG (String name) {
        sceneName = name;
        backgroundColor = Color.white;
        sceneMouseL = createClickInteraction();
        sceneKeyL = createKeyInteraction();
    
    }

    public SceneCG (String name, Color bgColor, BaseObjectCG[] objects, int[] orders, ActionInterfaceCG newPreFunct, ActionInterfaceCG newPostFunct, KeyInterfaceCG newReleaseFunct) {
        sceneName = name;
        backgroundColor = bgColor;
        preSceneFunction = newPreFunct;
        postSceneFunction = newPostFunct;
        keyReleasedFunction = newReleaseFunct;

        sceneMouseL = createClickInteraction();
        sceneKeyL = createKeyInteraction();

        for (int i = 0; i < objects.length; i++) {
            addObject(objects[i], orders[i]);
        }
    }

    public void generateScene(Graphics2D g2d) {
        sceneWindow.setBackground(backgroundColor);

        if (preSceneFunction != null) {
            preSceneFunction.apply();
        }

        for (BaseObjectCG object : sceneObjects) {
            object.generateObject(g2d);
        }

        if (postSceneFunction != null) {
            postSceneFunction.apply();
        }
        
    }

    public void addObject(BaseObjectCG object, int order) {
        if (object instanceof ClickableGroupObjectCG clickable) {
            mouseClickables.add(clickable);
        }

        if (object instanceof TextBoxCG textBox) {
            textBoxes.add(textBox);
        }

        object.setOrder(order);
        sceneObjects.add(object);
        sceneObjects.sort(Comparator.comparing(BaseObjectCG::getOrder));
    }

    public void removeObject(BaseObjectCG object) {
        if (object instanceof ClickableGroupObjectCG clickable) {
            mouseClickables.remove(clickable);
        }

        if (object instanceof TextBoxCG textBox) {
            textBoxes.remove(textBox);
        }

        sceneObjects.remove(object);
    }


    public void setBackgroundColor(Color newColor) {
        backgroundColor = newColor;
    }

    public void setWindow(JPanel newWindow) {
        sceneWindow = newWindow;
    }

    public MouseListener createClickInteraction() {
        
        return new MouseListener() { 


            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // Get the location of the mouse click
                int x = e.getX();
                int y = e.getY();

                // Update the click pos
                XYPointCG clickPoint = new XYPointCG(x, y);

                for (ClickableGroupObjectCG clickableObject : mouseClickables) {
                    if (clickableObject instanceof TextBoxCG) {
                        TextBoxCG curBox = (TextBoxCG) clickableObject;
                        curBox.isTyping = false;

                        if (curBox.getText().length() == 0) {
                            curBox.textTyped = false;
                            curBox.textObject.setColor(curBox.baseColor);
                            curBox.setText(curBox.baseText);
                        }
                    }

                    clickableObject.checkClick(clickPoint);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public KeyListener createKeyInteraction() {
        return new KeyListener() {
            boolean isTyping = false;

            @Override
            public void keyTyped(KeyEvent e) {

                for (TextBoxCG textBox : textBoxes) {
                    if (textBox.enabled && textBox.isTyping) {
                        textBox.checkKeyTyped(e);
                        isTyping = true;
                    }
                }

                if (!isTyping) {

                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (TextBoxCG textBox : textBoxes) {
                    if (textBox.enabled && textBox.isTyping) {
                        textBox.checkKeyPressed(e);
                        isTyping = true;
                    }
                }

                if (!isTyping) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (TextBoxCG textBox : textBoxes) {
                    if (textBox.enabled && textBox.isTyping) {
                        textBox.checkKeyReleased(e);
                        isTyping = true;
                    }
                }
                
                if (!isTyping && keyReleasedFunction != null) {
                    
                    
                    keyReleasedFunction.apply(e);
                }

                isTyping = false;
            }
            
        };
    }
}
