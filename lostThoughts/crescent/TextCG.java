package lostThoughts.crescent;

import java.awt.*;


public class TextCG extends BaseObjectCG{

    
    public String thisText;
    public int thisScale;
    public Color thisColor;
    public Font thisFont;

    public CenteringStyle centerStyle;
    public enum CenteringStyle {
        // LeftRight, UpDown
        LT(00),
        LEFT(01),
        LB(02),
        BOTTOM(12),
        RB(22),
        RIGHT(21),
        RT(20),
        TOP(10),
        CENTER(11);

        private final int spotNum;

        // Constructor to initialize the dayNumber
        CenteringStyle(int spotNum) {
            this.spotNum = spotNum;
        }

        public int getNum() {
            return spotNum;
        }
    }
    
    //Centered Text
    /**
     * Generates text at the given startpoint based on centering style
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code centering}
     * @param centering = What centering the text should follow
     */
    public TextCG(String text, int scale, Color color, XYPointCG startPoint, CenteringStyle centering) {
        thisText = text;
        thisScale = scale;
        thisColor = color;
        basePoint = startPoint;
        updateCurrentPoint();
        centerStyle = centering;
        thisFont = new Font("Arial", Font.PLAIN, thisScale);

    }

    //Centered Text
    /**
     * Generates text at the given startpoint based on centering style
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code centering}
     * @param centering = What centering the text should follow
     */
    public TextCG(String text, Font curFont, Color color, XYPointCG startPoint, CenteringStyle centering) {
        thisText = text;
        thisScale = curFont.getSize();
        thisColor = color;
        basePoint = startPoint;
        updateCurrentPoint();
        centerStyle = centering;
        thisFont = curFont;

    }

    /**
     * Generates a the attached text when given the graphics needed
     * 
     * @param g2d = The Graphics2D to add the text to
     * 
     */
    @Override
    public void generateObject(Graphics2D g2d) {
        g2d.setFont(thisFont);
        g2d.setColor(thisColor);

        // Get the FontMetrics to measure the text
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(thisText);
        int textHeight = metrics.getHeight();
        int textAscent = metrics.getAscent();
        
        int genX = 0;
        int genY = 0;
        
        int digitX = centerStyle.getNum() / 10;
        int digitY = centerStyle.getNum() % 10;

        if (digitX == 0) {
            genX = (int) currentPoint.getX();
        }
        else if (digitX == 1) {
            genX = (int) currentPoint.getX() - textWidth / 2;
        }
        else if (digitX == 2) {
            genX = (int) currentPoint.getX() - textWidth;
        }

        if (digitY == 0) {
            genY = (int) currentPoint.getY();
        }
        else if (digitY == 1) {
            genY = (int) currentPoint.getY() + textAscent - textHeight / 2;
        }
        else if (digitY == 2) {
            genY = (int) currentPoint.getY() - textHeight;
        }


        g2d.drawString(thisText, genX, genY);
    }


// [Gets]
    /**
     * Gets the display text
     * 
     * @return {@code String} = This objects text
     * 
     */
    public String getText () {
        return thisText;
    }

    /**
     * Gets the text scale
     * 
     * 
     */
    public int getScale () {
        return thisScale;
    }

    /**
     * Gets the text color
     * 
     * 
     */
    public Color getColor () {
        return thisColor;
    }

    /**
     * Gets the text font
     * 
     */
    public Font getFont () {
        return thisFont;
    }

//

// [Sets]
    /**
     * Sets the display text
     * 
     * @param newText = The text to change to
     * 
     */
    public void setText (String newText) {
        thisText = newText;
    }

    /**
     * Sets the text scale
     * 
     * @param newScale = The scale to change to
     * 
     */
    public void setScale (int newScale) {
        thisScale = newScale;
    }

    /**
     * Sets the text color
     * 
     * @param newColor = The color to change to
     * 
     */
    public void setColor (Color newColor) {
        thisColor = newColor;
    }

    /**
     * Sets the text font name
     * 
     * @param fontName = The name of font to use
     * 
     */
    public void setFontName (String fontName) {
        thisFont = new Font(fontName, Font.PLAIN, thisScale);
    }

    /**
     * Sets the text font style
     * 
     * 
     */
    public void setFontStyle (String fontStyle) {
        thisFont = new Font(thisFont.getFontName(), Font.PLAIN, thisScale);
    }
//
}
