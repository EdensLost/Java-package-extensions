package lostThoughts.crescent;

import lostThoughts.helpingHands.UtilH;

public class XYPointCG {
    private double[] point;

    //XYPoint 
    /**
     * Used to generate a new point
     * 
     * @param newX = The x point to set
     * @param newY = The y point to set
     */
    public XYPointCG(double newX, double newY) {
        point = new double[]{newX, newY};
    }

    public XYPointCG clone() {
        return new XYPointCG(getX(), getY());
    }

    // To String
    @Override
    public String toString() {
        String xyString = "(" + this.getX() + ", " + this.getY() + ")";
        return xyString;
    }


//  ***[Sets]***  \\
    //Set Point 
    /**
     * Used to set the x and y of the point
     * 
     * @param newX = The x point to set
     * @param newY = The y point to set
     */
    public void setPoint(double newX, double newY) {
        point = new double[]{newX, newY};
    }

    //Set X 
    /**
     * Used to set the x of the point
     * 
     * @param newX = The x point to set
     */
    public void setX(double newX) {
        point[0] = newX;
    }

    //Set Y 
    /**
     * Used to set the y of the point
     * 
     * @param newY = The y point to set
     */
    public void setY(double newY) {
        point[1] = newY;
    }
//

//  ***[Gets]***  \\
    //Get Point 
    /**
     * Gets the point
     * 
     * @return {@code double[]} = The x and y of the XYPoint
     */
    public double[] getPoint() {
        return point;
    }

    //Get X 
    /**
     * Gets the x
     * 
     * @return {@code double} = The x the XYPoint
     */
    public double getX() {
        return getPoint()[0];
    }

    //Get Y 
    /**
     * Gets the y
     * 
     * @return {@code double} = The y the XYPoint
     */
    public double getY() {
        return getPoint()[1];
    }
//

//  ***[Methods]***  \\
    //Move X 
    /**
     * Used to move the x of the point
     * 
     * @param deltaX = The change x point
     * 
     * @return {@code XYPointCG} = The attached XYPoint
     */
    public XYPointCG moveX(double deltaX) {
        setX(getX() + deltaX);
        return this;
    }

    //Move Y 
    /**
     * Used to move the y of the point
     * 
     * @param deltaY = The change in y point
     * 
     * @return {@code XYPointCG} = The attached XYPoint
     */
    public XYPointCG moveY(double deltaY) {
        setY(getY() + deltaY);
        return this;
    }

    public XYPointCG movePoint(XYPointCG deltaPoint) {
        moveX(deltaPoint.getX());
        moveY(deltaPoint.getY());
        return this;
    }

    // Find center
    /**
     * Used to find the center between two points
     * 
     * @param firstPoint = The first point
     * @param secondPoint = The second point
     * 
     * @return {@code XYPoint} = The center point
     */
    public static XYPointCG findCenter(XYPointCG firstPoint, XYPointCG secondPoint) {
        double centerX = 0;
        double centerY = 0;

        // Centers the x
        if (firstPoint.getX() > secondPoint.getX()) {
            centerX = firstPoint.getX() - secondPoint.getX();
            centerX /= 2;
            centerX = secondPoint.getX() + centerX;
        }
        else {
            centerX = secondPoint.getX() - firstPoint.getX();
            centerX /= 2;
            centerX = firstPoint.getX() + centerX;
        }

        

        // Centers the y
        if (firstPoint.getY() > secondPoint.getY()) {
            centerY = firstPoint.getY() - secondPoint.getY();
            centerY /= 2;
            centerY = secondPoint.getY() + centerY;
        }
        else {
            centerY = secondPoint.getY() - firstPoint.getY();
            centerY /= 2;
            centerY = firstPoint.getY() + centerY;
        }

        // Returns the centered point
        return new XYPointCG(centerX, centerY);
    }

    // Point distance
    /**
     * Used to find the euclidean distance between two points
     * <p> d = √[(x2 – x1)2 + (y2 – y1)2]
     * 
     * @param firstPoint = The first point
     * @param secondPoint = The second point
     * 
     * @return {@code double} = The distance between both points
     */
    public static double pointDist(XYPointCG firstPoint, XYPointCG secondPoint) {
        double preSquare = Math.pow((secondPoint.getX() - firstPoint.getX()) * 2, 2) + Math.pow((secondPoint.getY() - firstPoint.getY()) * 2, 2);
        return Math.sqrt(preSquare);
    }

    // This distance
    /**
     * Used to find the euclidean distance between this point and another
     * <p> d = √[(xC – xT)2 + (yC – yT)2]
     * 
     * @param compPoint = The compared point
     * 
     * @return {@code double} = The distance between both points
     */
    public double thisDist(XYPointCG compPoint) {
        double preSquare = Math.pow((compPoint.getX() - this.getX()) * 2, 2) + Math.pow((compPoint.getY() - this.getY()) * 2, 2);
        return Math.sqrt(preSquare);
    }

    //Point Near Point
    /**
     * Checks if one point is close to another.
     * 
     * @param firstPoint = The first XYPoint
     * @param secondPoint = The second XYPoint
     * @param maxDist = The maximum distance the points can be between
     * 
     * @return {@code boolean} = If the point is close enough
     */
    public static boolean pointNearPoint(XYPointCG firstPoint, XYPointCG secondPoint, double maxDist) {
        return pointDist(firstPoint, secondPoint) <= maxDist;
    }

    //This Near Point
    /**
     * Checks if one point is close to another.
     * 
     * @param secondPoint = The second XYPoint
     * @param maxDist = The maximum distance the points can be between
     * 
     * @return {@code boolean} = If the point is close enough
     */
    public boolean pointNearPoint(XYPointCG secondPoint, double maxDist) {
        return thisDist(secondPoint) <= maxDist;
    }

    //Generate new inbounds point
    /**
     * Generates a new point in the bounds from (0, 0) to the max point
     * 
     * @param maxPoint = The maximum width and height of the region
     * @param shapeDim = The width and height of the shape
     * 
     * @return {@code XYPoint} = The inbounds XYPoint
     */
    public static XYPointCG newInboundsPoint(XYPointCG maxPoint, double shapeWidth, double shapeHeight) {
        double halfWidth = shapeWidth / 2;
        double halfHeight = shapeHeight / 2;
        return new XYPointCG(UtilH.newRand(maxPoint.getX() - halfWidth, halfWidth), UtilH.newRand(maxPoint.getY() - halfHeight, halfHeight));
    }

    //Generate new inbounds point
    /**
     * Generates a new point in the bounds from (0, 0) to the max point
     * 
     * @param maxPoint = The maximum width and height of the region
     * @param shapeDim = The width and height of the shape
     * 
     * @return {@code XYPoint} = The inbounds XYPoint
     */
    public static XYPointCG newInboundsPoint(XYPointCG maxPoint, XYPointCG minPoint, double shapeWidth, double shapeHeight) {
        double halfWidth = shapeWidth / 2;
        double halfHeight = shapeHeight / 2;
        return new XYPointCG(UtilH.newRand(maxPoint.getX() - halfWidth, minPoint.getX() + halfWidth), 
                            UtilH.newRand(maxPoint.getY() - halfHeight, minPoint.getY() + halfHeight));
    }
//
}   
