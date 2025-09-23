package lostThoughts.crescent;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GroupObjectCG extends BaseObjectCG{
    public int orderLevel;

    public ArrayList<BaseObjectCG> objectGroup = new ArrayList<>();

    public XYPointCG basePoint = new XYPointCG(0, 0);
    public XYPointCG offsetPoint = new XYPointCG(0, 0);
    public XYPointCG currentPoint = new XYPointCG(0, 0);
    
    public void generateObject(Graphics2D g2d) { 
        for (BaseObjectCG object : objectGroup) {
            object.generateObject(g2d);
        }
    }

// TITLE [Sets]
    public void setOrder(int order) {
        this.orderLevel = order;
    }

    public void setStartPoint (XYPointCG newStartPoint) {
        basePoint = newStartPoint;

        for (BaseObjectCG object : objectGroup) {
            object.setStartPoint(basePoint);
            object.updateCurrentPoint();
        }
    }

    public void setOffsetPoint(XYPointCG newOffsetPoint) {
        offsetPoint = newOffsetPoint;

        for (BaseObjectCG object : objectGroup) {
            object.setOffsetPoint(offsetPoint);
            object.updateCurrentPoint();
        }
    }

    public void updateCurrentPoint() {
        currentPoint.setX(basePoint.getX() + offsetPoint.getX());
        currentPoint.setY(basePoint.getY() + offsetPoint.getY());

        for (BaseObjectCG object : objectGroup) {
            object.updateCurrentPoint();
        }
    }
//

// TITLE [Gets]
    public int getOrder() {
        return orderLevel;
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
        setOffsetPoint(new XYPointCG(getOffsetPoint().getX() + xDelta, getOffsetPoint().getY() + yDelta));
    }

    public void move(XYPointCG delatPoint) {
        setOffsetPoint(new XYPointCG(getOffsetPoint().getX() + delatPoint.getX(), getOffsetPoint().getY() + delatPoint.getY()));
    }
//
}
