package lostThoughts.crescent;

public class ClickableObject extends BaseObjectCG {
    public boolean enabled = true;
    public XYPointCG[] clickZone;
    public ActionInterfaceCG clickAction;

    public boolean isInZone(XYPointCG compPoint) {
        
        if (clickZone[0].getX() <= compPoint.getX() && clickZone[1].getX() >= compPoint.getX()) {
            if (clickZone[0].getY() <= compPoint.getY() && clickZone[1].getY() >= compPoint.getY()) {
                return true;
            }
        }

        return false;
    }
    
    public void checkClick(XYPointCG compPoint) { 
        if (enabled && isInZone(compPoint)) {
            clickAction.apply();
        }
    }

    public void setEnabled(boolean isEnabled) {
        enabled = isEnabled;
    }

    public void setClickZone(double width, double height) {
        XYPointCG topLeft = new XYPointCG(basePoint.getX() - (width / 2), basePoint.getY() - (height / 2));
        XYPointCG bottomRight = new XYPointCG(basePoint.getX() + (width / 2), basePoint.getY() + (height / 2));

        clickZone = new XYPointCG[] {topLeft, bottomRight};
    }

    @Override
    public ClickableObject move(double xDelta, double yDelta) {
        setOffsetPoint(new XYPointCG(getOffsetPoint().getX() + xDelta, getOffsetPoint().getY() + yDelta));
        clickZone[0].setPoint(clickZone[0].getX() + xDelta, clickZone[0].getY() + yDelta);
        clickZone[1].setPoint(clickZone[1].getX() + xDelta, clickZone[1].getY() + yDelta);

        return this;
    }

    @Override
    public ClickableObject move(XYPointCG deltaPoint) {
        double newX = deltaPoint.getX();
        double newY = deltaPoint.getY();

        setOffsetPoint(new XYPointCG(getOffsetPoint().getX() + newX, getOffsetPoint().getY() + newY));
        clickZone[0].setPoint(clickZone[0].getX() + newX, clickZone[0].getY() + newY);
        clickZone[1].setPoint(clickZone[1].getX() + newX, clickZone[1].getY() + newY);

        return this;
    }
}
