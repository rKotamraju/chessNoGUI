package chess;

public class Pawn extends Piece{

    public Pawn(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wp" : "bp";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a Pawn");

        return false;
    }
}