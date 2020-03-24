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
        //(oFile: 6, oRank: 0, nFile: 5, nRank: 1)
        int fdist = Math.abs(oFile - nFile);
        int rdist = Math.abs(oRank - nRank);

        //insert castling right here
        /**
         * check hasMoved, check nRank of the kings and make sure its 7 or 0, check that rank is not changing
         */



        if(fdist > 1 || rdist > 1){
            System.out.println("King can only move one space");
            return false;
        }

        setKingPosition(nRank, nFile);

        if(super.move(board, oFile, oRank, nFile, nRank) && (Chess.check(board, this)==false)){
            System.out.println("Updating King's position");
            return true;
        }

        setKingPosition(oRank,oFile);
        return false;

    }

    public boolean setKingPosition(int rank, int file){
        this.file = file;
        this.rank = rank;

        return true;

    }
}
