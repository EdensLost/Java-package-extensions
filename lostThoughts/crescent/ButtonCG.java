package lostThoughts.crescent;

import java.awt.*;

import lostThoughts.crescent.TextCG.CenteringStyle;

public class ButtonCG extends ClickableGroupObjectCG{

    public XYPointCG thisButtonWidthHeight;
    private DynamicShapeCG thisButtonShape;
    private TextCG thisButtonText;

    //Button
    /**
     * Used to generate a new centered button
     * 
     * @param centerPoint = The center point of the button
     * @param width = The width of the button
     * @param height = The height of the button
     * @param buttonColor = The color of the button in the background
     * @param buttonText = The text the button displays
     * @param textScale = The scale of the button text
     * @param textColor = The color of the button text
     */
    public ButtonCG(XYPointCG centerPoint, double width, double height, Color buttonColor, String buttonText, int textScale, Color textColor, ActionInterfaceCG clickFunction, ActionInterfaceCG missFunction) {
        clickAction = clickFunction;
        noClickAction = missFunction;
        basePoint = centerPoint;
        offsetPoint = new XYPointCG(0, 0);
        currentPoint = basePoint;
        thisButtonShape = genButtonShape(centerPoint, width, height, buttonColor);
        thisButtonText = genButtonText(buttonText, textScale, textColor);
        objectGroup.add(thisButtonShape);
        objectGroup.add(thisButtonText);
    }

    //Button
    /**
     * Used to generate a new centered button
     * 
     * @param centerPoint = The center point of the button
     * @param width = The width of the button
     * @param height = The height of the button
     * @param buttonColor = The color of the button in the background
     * @param buttonText = The text the button displays
     * @param textFont = The Font of the button text
     * @param textColor = The color of the button text
     * @param clickFunction = The function that is run when the button is clicked
     */
    public ButtonCG(XYPointCG centerPoint, double width, double height, Color buttonColor, String buttonText, Font textFont, Color textColor, ActionInterfaceCG clickFunction, ActionInterfaceCG missFunction) {
        clickAction = clickFunction;
        noClickAction = missFunction;
        basePoint = centerPoint;
        offsetPoint = new XYPointCG(0, 0);
        currentPoint = basePoint;
        thisButtonShape = genButtonShape(centerPoint, width, height, buttonColor);
        thisButtonText = genButtonText(buttonText, textFont, textColor);
        objectGroup.add(thisButtonShape);
        objectGroup.add(thisButtonText);
    }

// TITLE [Gen button rect]
    //Gen button shape
    /**
     * Used to generate a new button shape
     * 
     * @param topLeft = The top left point of the button
     * @param bottomRight = The bottom right point of the button
     * @param buttonColor = The color of the button in the background
     * 
     * @return {@code DynamicShape} = The shape for the button
     */
    private DynamicShapeCG genButtonShape(XYPointCG centerPoint, double width, double height, Color buttonColor) {
        setClickZone(width, height);

        thisButtonWidthHeight = new XYPointCG(width, height);

        XYPointCG[] pointsList = new XYPointCG[] {new XYPointCG( -(width / 2.0), -(height / 2.0)), 
                                                new XYPointCG((width / 2.0), -(height / 2.0)), 
                                                new XYPointCG((width / 2.0), (height / 2.0)), 
                                                new XYPointCG(-(width / 2.0), (height / 2.0))};

        
        

        DynamicShapeCG returnShape = new DynamicShapeCG(1, buttonColor, centerPoint, pointsList);

        return returnShape;
    }
//

// TITLE [Gen button text]
    //Gen button text
    /**
     * Used to generate new button text
     * 
     * @param buttonText = The String text of the button
     * @param textScale = The scale of the button text
     * @param textColor = The Color of the button text
     * 
     * @return {@code DynamicShape} = The shape for the button
     */
    private TextCG genButtonText(String buttonText, int textScale, Color textColor) {
        XYPointCG centerPoint = XYPointCG.findCenter(clickZone[0], clickZone[1]);

        TextCG returnText = new TextCG(buttonText, textScale, textColor, centerPoint, CenteringStyle.CENTERED);

        return returnText;
    }

    /**
     * Used to generate new button text
     * 
     * @param buttonText = The String text of the button
     * @param textFont = The Font of the button text
     * @param textColor = The Color of the button text
     * 
     * @return {@code DynamicShape} = The shape for the button
     */
    private TextCG genButtonText(String buttonText, Font textFont, Color textColor) {
        XYPointCG centerPoint = XYPointCG.findCenter(clickZone[0], clickZone[1]);

        TextCG returnText = new TextCG(buttonText, textFont, textColor, centerPoint, CenteringStyle.CENTERED);

        return returnText;
    }
//

// TITLE [Sets]
    // Set button function
    /**
     * Sets the function of the button
     * 
     * @param newDim = The new dimensions for the button
     */
    public void setButtonFunction(ActionInterfaceCG clickFunction) {
        clickAction = clickFunction;
    }

    // Set button shape
    /**
     * Sets the shape of the button
     * 
     * @param newDim = The new dimensions for the button
     */
    public void setButtonShape(XYPointCG centerPoint, double width, double height, Color buttonColor) {
        thisButtonShape = genButtonShape(centerPoint, width, height, buttonColor);
    }

    // Set button text
    /**
     * Sets the text of the button
     * 
     * @return {@code TextCG} = The button text
     */
    public void setButtonText(String buttonText, Font textFont, Color textColor) {
        thisButtonText = genButtonText(buttonText, textFont, textColor);
    }

    // Set center point
    /**
     * Sets the center of the button
     * 
     * @return {@code XYPointCG} = The button center
     */
    public void setButtonCenter(XYPointCG newCenter) {
        thisButtonShape = genButtonShape(newCenter, thisButtonWidthHeight.getX(), thisButtonWidthHeight.getY(), thisButtonShape.getColor());
        thisButtonText = genButtonText(thisButtonText.getText(), thisButtonText.getFont(), thisButtonText.getColor());
    }
//

// TITLE [Gets]
    // Get button dimension
    /**
     * Returns the dimensions of the button
     * 
     * @return {@code XYPointCG[]} = The button dimensions
     */
    public XYPointCG[] getDim() {
        return clickZone;
    }

    // Get button shape
    /**
     * Returns the shape of the button
     * 
     * @return {@code DynamicShapeCG} = The button shape
     */
    public DynamicShapeCG getButtonShape() {
        return thisButtonShape;
    }

    // Get button text
    /**
     * Returns the text of the button
     * 
     * @return {@code TextCG} = The button text
     */
    public TextCG getButtonText() {
        return thisButtonText;
    }
//
}
