package lostThoughts.crescent;

import java.awt.*;


public class TextCG extends BaseObjectCG{

    
    private String thisText;
    private int thisScale;
    private Color thisColor;
    private Font thisFont;

    public CenteringStyle centerStyle;
    public enum CenteringStyle {
        NOT,
        CENTERED,
        VERTICAL
    }
    
    //Centered Text
    /**
     * Generates text at the given centerpoint
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code isCentered}
     * @param isCentered = If the text is centered
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
     * Generates text at the given centerpoint
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code isCentered}
     * @param isCentered = If the text is centered
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
        
        if (centerStyle == CenteringStyle.CENTERED) {
            g2d.setFont(thisFont);
            g2d.setColor(thisColor);

            // Get the FontMetrics to measure the text
            FontMetrics metrics = g2d.getFontMetrics();
            int textWidth = metrics.stringWidth(thisText);
            int textHeight = metrics.getHeight();
            int textAscent = metrics.getAscent();

            // Calculate the position to center the text
            int centeredX = (int) currentPoint.getX() - textWidth / 2;
            int centeredY = (int) currentPoint.getY() + textAscent - textHeight / 2;

            // Draws the text
            g2d.drawString(thisText, centeredX, centeredY);
        }
        else if (centerStyle == CenteringStyle.NOT) {
            g2d.setFont(thisFont);
            g2d.setColor(thisColor);

            g2d.drawString(thisText, (int) currentPoint.getX(), (int) currentPoint.getY());
        }
        else if (centerStyle == CenteringStyle.VERTICAL) {
            g2d.setFont(thisFont);
            g2d.setColor(thisColor);

            // Get the FontMetrics to measure the text
            FontMetrics metrics = g2d.getFontMetrics();
            int textHeight = metrics.getHeight();
            int textAscent = metrics.getAscent();

            // Calculate the position to center the text
            int centeredY = (int) currentPoint.getY() + textAscent - textHeight / 2;

            // Draws the text
            g2d.drawString(thisText, (int) currentPoint.getX(), centeredY);
        }
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
