package chess;

public class King extends Queen{

    public King(boolean isWhite){
        super(isWhite);

        this.rank = isWhite ? 7 : 0;
        this.file = 4;

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

        if(super.move(board, oFile, oRank, nFile, nRank)){
            setKingPosition(nRank, nFile);
            return true;
        }

        return false;

    }

    public boolean setKingPosition(int rank, int file){
        this.file = file;
        this.rank = rank;

        return true;

    }
}
