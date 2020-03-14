package chess;

public abstract class Piece {
    char file;
    char rank;
    boolean isWhite;
    String name;

    public abstract void move();

}
