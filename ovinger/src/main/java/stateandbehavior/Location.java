package stateandbehavior;

public class Location {

    int xPos;
    int yPos;

    public void up() {
        yPos -= 1;
    }
    public void down() {
        yPos += 1;
    }
    
    public void left() {
        xPos -= 1;
    }

    public void right() {
        xPos += 1;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    @Override
    public String toString() {
        return "Etter figuren har beveget seg er den i punktet ("+this.xPos+","+this.yPos+")";
    }

    public static void main(String[] args) {
        Location location = new Location();
        location.up();
        location.up();
        location.right();
        location.right();
        System.out.println(location);
    }
}
