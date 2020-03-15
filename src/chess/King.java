package chess;

public class King extends Piece{

    public King(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wK" : "bK";
    }

    @Override
    public boolean move(String omove, String nmove) {
        System.out.println("Player is moving a King");

        return false;
    }
}
