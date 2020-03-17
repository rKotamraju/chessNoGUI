package chess;

public class Rook extends Piece{

    public Rook(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wR" : "bR";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank)  {
        System.out.println("Player is moving a Rook");


        return false;
    }
}