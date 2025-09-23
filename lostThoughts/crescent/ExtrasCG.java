package lostThoughts.crescent;

import java.awt.*;

import lostThoughts.helpingHands.UtilH;

public class ExtrasCG {
    

    // Random Color
    /**
     * Used to generate a random color
     * 
     * @return {@code Color} = The random color
     */
    public static Color randomColor() {
        try {
            return new Color(UtilH.randInt(256), UtilH.randInt(256), UtilH.randInt(256));
        } catch (Exception e) {
            return Color.black;
        }
    }
    //

    // Color lerp
    /** Interpolates between two colors
     * 
     * @param start = The color to start at
     * @param end = The color to end at
     * @param curPercent = The current percent to interpolate
     * 
     * @return {@code Color} = The interpolated color
     */
    public static Color lerpColors(Color start, Color end, double curPercent) {

        int red = (int) UtilH.doubLerp(start.getRed(), end.getRed(), curPercent);
        int green = (int) UtilH.doubLerp(start.getGreen(), end.getGreen(), curPercent);
        int blue = (int) UtilH.doubLerp(start.getBlue(), end.getBlue(), curPercent);
        int alpha = (int) UtilH.doubLerp(start.getAlpha(), end.getAlpha(), curPercent);

        return new Color(red, green, blue, alpha);
    }
    //

    // Point lerp
    /** Interpolates between two points
     * 
     * @param start = The point to start at
     * @param end = The point to end at
     * @param curPercent = The current percent to interpolate
     * 
     * @return {@code XYPointCG} = The interpolated point
     */
    public static XYPointCG lerpPoints(XYPointCG start, XYPointCG end, double curPercent) {
        double x = UtilH.doubLerp(start.getX(), end.getX(), curPercent);
        double y = UtilH.doubLerp(start.getY(), end.getY(), curPercent);


        return new XYPointCG(x, y);
    }
    //
}
