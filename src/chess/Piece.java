package chess;

public abstract class Piece {
    char file;
    char rank;
    boolean isWhite;
    String name;
    boolean hasMoved;

    public abstract boolean move(String omove, String nmove);

    public String toString(){
        return name;
    }

}
