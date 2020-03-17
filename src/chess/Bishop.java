package chess;

public class Bishop extends Piece{

    public Bishop(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wB" : "bB";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank){
        System.out.println("Player is moving a Bishop");

        return false;
    }
}
