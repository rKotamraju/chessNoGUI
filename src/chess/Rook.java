package chess;

public class Rook extends Queen{

    public Rook(boolean isWhite){

        super(isWhite);
        //this.isWhite = isWhite;
        this.name = isWhite ? "wR" : "bR";
    }

    @Override
    public boolean moveDiagonally(Piece[][] board, int oFile, int oRank, int nFile, int nRank) {
        return false;
    }
}