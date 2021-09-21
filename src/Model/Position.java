package Model;

public class Position {
    private int id;
    private int lane;
    private int shelf;


    public Position() {
        this.lane = 0;
        this.shelf = 0;
    }

    public Position(int lane, int shelf) {
        this.lane = lane;
        this.shelf = shelf;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }
}
