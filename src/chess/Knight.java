package chess;

public class Knight extends Piece{

    public Knight(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wK" : "bK";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a Pawn");

        return false;
    }
}