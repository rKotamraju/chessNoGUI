package chess;

public class Knight extends Piece{

    public Knight(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wN" : "bN";
    }

    @Override
    public boolean move(Piece[][] board, int oFile, int oRank, int nFile, int nRank) {
        //System.out.println("Player is moving a"+name);


        if((Math.abs(oFile-nFile)+Math.abs(oRank-nRank) == 3) && (oFile != nFile && oRank != nRank)){
            if (board[nRank][nFile] != null) {
                if (board[oRank][oFile].isWhite == board[nRank][nFile].isWhite) {
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