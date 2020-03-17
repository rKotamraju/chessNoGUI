package chess;

public class Queen extends Piece{

    public Queen(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wQ" : "bQ";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank)  {
        System.out.println("Player is moving a Queen");

        return false;
    }
}