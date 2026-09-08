package lostThoughts.crescent;

import java.awt.*;


public class MLTextCG extends TextCG{
    public double thisLineSpacing;

    public LinePositioningStyle linePosStyle;
    public enum LinePositioningStyle {
        DESCENDING,
        CENTERED,
        ASCENDING
    }
    
    
    /**
     * Generates multiline text (use "\n") at the given startpoint based on centering style and line positioning style
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code centering}
     * @param centering = What centering the text should follow
     * @param linePos = What line positioning style the multilines should follow
     * @param lineSpacing = The multiplicative distance between each line based on the height of the text (1 = text height)
     */
    public MLTextCG(String text, int scale, Color color, XYPointCG startPoint, CenteringStyle centering, LinePositioningStyle linePos, double lineSpacing) {
        super(text, new Font("Arial", Font.PLAIN, scale), color, startPoint, centering);
        linePosStyle = linePos;
        thisLineSpacing = lineSpacing;
    }

    
    /**
     * Generates multiline text (use "\n") at the given startpoint based on centering style and line positioning style
     * 
     * @param text = The text to display
     * @param scale = The scale of the text
     * @param color = The color of the text
     * @param startPoint = The point the text draws based on {@code centering}
     * @param centering = What centering the text should follow
     * @param linePos = What line positioning style the multilines should follow
     * @param lineSpacing = The multiplicative distance between each line based on the height of the text (1 = text height)
     */
    public MLTextCG(String text, Font curFont, Color color, XYPointCG startPoint, CenteringStyle centering, LinePositioningStyle linePos, double lineSpacing) {
        super(text, curFont, color, startPoint, centering);
        linePosStyle = linePos;
        thisLineSpacing = lineSpacing;
    }

    /**
     * Generates a the attached text when given the graphics needed
     * 
     * @param g2d = The Graphics2D to add the text to
     * 
     */
    @Override
    public void generateObject(Graphics2D g2d) {
        drawMultiline(g2d);
    }

    private void drawMultiline(Graphics2D g2d) {
        g2d.setFont(thisFont);
        g2d.setColor(thisColor);

        // Get the FontMetrics to measure the text
        FontMetrics metrics = g2d.getFontMetrics();
        int textHeight = metrics.getHeight();
        int textAscent = metrics.getAscent();

        String[] textLines = thisText.split("\n");

        XYPointCG newPoint;

        if (linePosStyle == LinePositioningStyle.ASCENDING) {
            newPoint = currentPoint.clone().moveY(-(textLines.length * thisLineSpacing * textHeight) + textHeight);
        }
        else if (linePosStyle == LinePositioningStyle.CENTERED) {
            newPoint = currentPoint.clone().moveY(-((textLines.length * thisLineSpacing * textHeight) / 2) + textHeight);
        }
        else {
            newPoint = currentPoint.clone();
        }

        for (String text : textLines) {
            int textWidth = metrics.stringWidth(text);
            
            int genX = 0;
            int genY = 0;
            
            int digitX = centerStyle.getNum() / 10;
            int digitY = centerStyle.getNum() % 10;

            // Left
            if (digitX == 0) {
                genX = (int) newPoint.getX();
            }
            // Center
            else if (digitX == 1) {
                genX = (int) newPoint.getX() - textWidth / 2;
            }
            // Right
            else if (digitX == 2) {
                genX = (int) newPoint.getX() - textWidth;
            }

            // Top
            if (digitY == 0) {
                genY = (int) newPoint.getY();
            }
            // Center
            else if (digitY == 1) {
                genY = (int) newPoint.getY() + textAscent - textHeight / 2;
            }
            // Bottom
            else if (digitY == 2) {
                genY = (int) newPoint.getY() + textAscent - textHeight;
            }


            g2d.drawString(text, genX, genY);

            newPoint.moveY(thisLineSpacing * textHeight);
        }
        
    }
}
