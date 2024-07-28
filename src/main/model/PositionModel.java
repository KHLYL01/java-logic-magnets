package main.model;

public class PositionModel implements Cloneable{
    private int x;
    private int y;

    public PositionModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PositionModel(PositionModel position) {
        this.x = position.x;
        this.y = position.y;
    }

    public boolean equal(PositionModel position){
        return position.x == this.x && position.y == this.y;
    }

    public boolean equalX(PositionModel position){
        return position.x == this.x;
    }

    public boolean equalY(PositionModel position){
        return position.y == this.y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "(" + x +","+ y + ")";
    }
}
