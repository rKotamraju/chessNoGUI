package chess;

public class Bishop extends Queen{

    public Bishop(boolean isWhite){
        super(isWhite);
        this.name = isWhite ? "wB" : "bB";
    }

    @Override
    public boolean moveStraight(Piece[][] board, int oFile, int oRank, int nFile, int nRank) {
        return false;
    }
}
