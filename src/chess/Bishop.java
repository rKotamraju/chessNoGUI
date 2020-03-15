package chess;

public class Bishop extends Piece{

    public Bishop(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wB" : "bB";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a Bishop");

        return false;
    }
}
