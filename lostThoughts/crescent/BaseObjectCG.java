package lostThoughts.crescent;

import java.awt.Graphics2D;

public class BaseObjectCG {
    public int orderLevel;

    public XYPointCG basePoint = new XYPointCG(0, 0);
    public XYPointCG offsetPoint = new XYPointCG(0, 0);
    public XYPointCG currentPoint = new XYPointCG(0, 0);
    
    public void generateObject(Graphics2D g2d) { }

// TITLE [Sets]
    public void setOrder(int order) {
        this.orderLevel = order;
    }

    public void setStartPoint (XYPointCG newStartPoint) {
        basePoint = newStartPoint;
        updateCurrentPoint();
    }

    public void setOffsetPoint(XYPointCG newOffsetPoint) {
        offsetPoint = newOffsetPoint;
        updateCurrentPoint();
    }

    public void updateCurrentPoint() {
        currentPoint.setX(basePoint.getX() + offsetPoint.getX());
        currentPoint.setY(basePoint.getY() + offsetPoint.getY());
    }
//

// TITLE [Gets]
    public int getOrder() {
        return this.orderLevel;
    }
    
    public XYPointCG getStartPoint () {
        return basePoint;
    }
    
    public XYPointCG getOffsetPoint() {
        return offsetPoint;
    }
    
    public XYPointCG getCurrentPoint() {
        return currentPoint;
    }

    //public 
//

// TITLE [Methods]
    public void move(double xDelta, double yDelta) {
        XYPointCG newOffset = new XYPointCG(getOffsetPoint().getX() + xDelta, getOffsetPoint().getY() + yDelta);
        setOffsetPoint(newOffset);
    }

    public void move(XYPointCG delatPoint) {
        XYPointCG newOffset = new XYPointCG(getOffsetPoint().getX() + delatPoint.getX(), getOffsetPoint().getY() + delatPoint.getY());
        setOffsetPoint(newOffset);
    }
//
}
