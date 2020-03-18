package chess;

public class Bishop extends Piece{

    public Bishop(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wB" : "bB";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank){
        System.out.println("Player is moving a Bishop");

        if((nFile != oFile) && (nRank != oRank)) {
            double slope = (nRank - oRank) / (nFile - oFile);

            if (Math.abs(slope) != 1) {
                System.out.println("Slope is not one");
                return false;
            }

            int r = oRank;
            int c = oFile;

            while( Math.abs(r - nRank) != 1){

                r = (oRank < nRank) ? ++r : --r; // south : north
                c = (oFile < nFile) ? ++c : --c;

                if (board[r][c] != null){
                    System.out.println("Something in the way: "+c+","+r);
                    return false;
                }
            }

            if(board[nRank][nFile] != null){
                if(board[oRank][oFile].isWhite == board[nRank][nFile].isWhite){
                    System.out.println("Don't kill ur friend");
                    return false;
                } else {
                    System.out.println("Slayed");
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
