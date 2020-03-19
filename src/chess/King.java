package chess;

public class King extends Queen{

    public King(boolean isWhite){
        super(isWhite);
        this.name = isWhite ? "wK" : "bK";
    }

    @Override
    public boolean move(Piece[][] board, int oFile, int oRank, int nFile, int nRank)  {

        int fdist = Math.abs(oFile - nFile);
        int rdist = Math.abs(oRank - nRank);

        if(fdist > 1 || rdist > 1){
            System.out.println("King can only move one space");
            return false;
        }

        return super.move(board, oFile, oRank, nFile, nRank);
    }
}
