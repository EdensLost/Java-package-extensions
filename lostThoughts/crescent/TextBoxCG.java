package lostThoughts.crescent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import lostThoughts.crescent.TextCG.CenteringStyle;

public class TextBoxCG extends ClickableGroupObjectCG{

    // Text box
    public XYPointCG textBoxSize;

    // Typing info
    public boolean isTyping = false;

    public AllowKeyInterfaceCG baseAllowInterface = (KeyEvent e)-> { return true;};
    public AllowKeyInterfaceCG extraKeyRestrictions;
    
    /**
     * Example of how to use:
     * 
     *@1 TextBoxCG.AllowKeyInterfaceCG loginRestrictions = (KeyEvent e) -> {
     *@1    int keyVal = e.getKeyChar();
     *@1    int[] restrictedKeys = {':', '*', '?', '<', '>', '/', '|', '\"'};
     *
     *@1    for (int code : restrictedKeys) {
     *          if (keyVal == code) {
     *             return false;
     *         }
     *      }
     *
     *@1    if (keyVal > 126) {
     *        return false;
     *      }
     *
     *@1    return true;
     *}
     */
    public interface AllowKeyInterfaceCG {
        boolean apply(KeyEvent e);
    }

    // Shape
    public DynamicShapeCG shapeObject;

    // Text
    public Color baseColor;
    public Color typedColor;
    public boolean textTyped = false;
    public String baseText;
    public String curText;
    public TextCG textObject;
    

    public TextBoxCG(XYPointCG centerPoint, double width, double height, Color backgroundColor, String originText, int textScale, Color untypedColor, Color textColor, AllowKeyInterfaceCG extraRestrictions) {
        basePoint = centerPoint;
        offsetPoint = new XYPointCG(0, 0);
        currentPoint = basePoint;

        shapeObject = genShape(centerPoint, width, height, backgroundColor);
        textObject = genText(originText, textScale, untypedColor);
        objectGroup.add(shapeObject);
        objectGroup.add(textObject);

        baseColor = untypedColor;
        typedColor = textColor;

        baseText = originText;
        curText = originText;
        clickAction = this::beginTyping;
        extraKeyRestrictions = extraRestrictions == null ? baseAllowInterface : extraRestrictions;
    }

    public TextBoxCG(XYPointCG centerPoint, double width, double height, Color backgroundColor, String originText, Font textFont, Color textColor, AllowKeyInterfaceCG extraRestrictions) {
        basePoint = centerPoint;
        offsetPoint = new XYPointCG(0, 0);
        currentPoint = basePoint;

        shapeObject = genShape(centerPoint, width, height, backgroundColor);
        textObject = genText(originText, textFont, textColor);
        objectGroup.add(shapeObject);
        objectGroup.add(textObject);

        baseText = originText;
        curText = originText;
        clickAction = this::beginTyping;
        extraKeyRestrictions = extraRestrictions == null ? baseAllowInterface : extraRestrictions;
    }

    // HEAD [Generation]
    private DynamicShapeCG genShape(XYPointCG centerPoint, double width, double height, Color backgroundColor) {
        setClickZone(width, height);

        textBoxSize = new XYPointCG(width, height);

        DynamicShapeCG returnShape = PredefinedShapeCG.rectangle(centerPoint, width, height, backgroundColor);

        return returnShape;
    }

    private TextCG genText(String text, int textScale, Color textColor) {
        XYPointCG centerPoint = XYPointCG.findCenter(clickZone[0], clickZone[1]);
        centerPoint = new XYPointCG(clickZone[0].getX(), centerPoint.getY());
        TextCG returnText = new TextCG(text, textScale, textColor, centerPoint, CenteringStyle.LEFT);

        return returnText;
    }

    private TextCG genText(String text, Font textFont, Color textColor) {
        XYPointCG centerPoint = XYPointCG.findCenter(clickZone[0], clickZone[1]);
        centerPoint = new XYPointCG(clickZone[0].getX(), centerPoint.getY());
        TextCG returnText = new TextCG(text, textFont, textColor, centerPoint, CenteringStyle.LEFT);

        return returnText;
    }

    // HEAD [Gets]
    public String getText() {
        return curText;
    }    

    public String returnText() {
        isTyping = false;
        textTyped = false;
        textObject.setColor(baseColor);
        String useText = curText;
        setText(baseText);
        return useText;
    }

    // HEAD [Sets]
    public void setText(String newText) {
        textObject.setText(newText);
        curText = newText;
    }

    // HEAD [Methods]

    public void beginTyping() { // The action that is run when the box is clicked
        isTyping = true;
    }

    public void checkKeyTyped(KeyEvent e) {
        char curKey = e.getKeyChar();
        int keyValue = Character.valueOf(curKey);
        if (keyValue >= 32 && keyValue != 127 && keyValue != 92 && extraKeyRestrictions.apply(e)) {
            FontMetrics tempMetrics = getFontMetrics(textObject);
            if (!textTyped) {
                textTyped = true;
                textObject.setColor(typedColor);

                setText("" + curKey);
            }
            else if (tempMetrics.stringWidth(getText() + curKey) < textBoxSize.getX()) {
                setText(getText() + curKey);
            }
            
        }
    }

    public FontMetrics getFontMetrics(TextCG textObject) {
        BufferedImage tempImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tempImg.createGraphics();
        g2d.setFont(textObject.getFont());
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.dispose();
        return metrics;
        //int width = fm.stringWidth(text);
        //g2d.dispose();
    }

    public void checkKeyPressed(KeyEvent e) {
        String curKey = KeyEvent.getKeyText(e.getKeyCode());
        String curText = getText();

        if (curKey.equals("Backspace")) {
            if (curText.length() != 0) {
                setText(curText.substring(0, curText.length() - 1));
            }
            
        }
        if (curKey.equals("Delete")) {
            if (curText.length() != 0) {
                setText(curText.substring(0, curText.length() - 1));
            }
            
        }
    }

    public void checkKeyReleased(KeyEvent e) {
        String curKey = KeyEvent.getKeyText(e.getKeyCode());
        //PyJav.printl("" + curKey);
        
        if (curKey.equals("Enter")) {
            isTyping = false;
            if (getText().length() == 0) {
                textTyped = false;
                textObject.setColor(baseColor);
                setText(baseText);
            }
        }
    }
}

