package chess;

public class King extends Piece{

    public King(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wK" : "bK";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank)  {
        System.out.println("Player is moving a King");


        return false;
    }
}
