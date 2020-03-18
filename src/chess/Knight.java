package chess;

public class Knight extends Piece{

    public Knight(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wN" : "bN";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank) {
        System.out.println("Player is moving a Knight");



        return false;
    }
}