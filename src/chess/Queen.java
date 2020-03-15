package chess;

public class Queen extends Piece{

    public Queen(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wQ" : "bQ";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a Pawn");

        return false;
    }
}