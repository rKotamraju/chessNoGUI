package chess;

public class Rook extends Piece{

    public Rook(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wR" : "bR";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a Rook");

        return false;
    }
}