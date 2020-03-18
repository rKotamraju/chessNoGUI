package chess;

public class Queen extends Piece{

    public Queen(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wQ" : "bQ";
    }

    @Override
    public boolean move(Piece[][] board, int oFile, int oRank, int nFile, int nRank)  {
        System.out.println("Player is moving a "+name);

        if(moveStraight(board, oFile, oRank, nFile, nRank)){
            return true;
        }else{
            return moveDiagonally(board, oFile, oRank, nFile, nRank);
        }

    }

    public boolean moveStraight(Piece[][] board, int oFile, int oRank, int nFile, int nRank){
        if((oFile == nFile) ^ (oRank == nRank)){ //Logical XOR, Either Files have to be the same or Ranks have to be the but not both
            int minRank = Math.min(oRank, nRank);
            int maxRank = Math.max(oRank, nRank);

            int minFile = Math.min(oFile, nFile);
            int maxFile = Math.max(oFile, nFile);

            if(oRank != nRank){ //vertical move
                for(int i = minRank+1; i< maxRank; i++){ //plus one bc we do not want to check the spot of the rook
                    if (board[i][oFile] != null) {
                        System.out.println("Something in the way: "+oFile+","+i);
                        return false;
                    }
                }
            } else { //horizontal move
                for(int i = minFile+1; i< maxFile; i++){
                    if (board[oRank][i] != null) {

                        System.out.println("Something in the way: "+oFile+","+i);
                        return false;
                    }
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

    public boolean moveDiagonally(Piece[][] board, int oFile, int oRank, int nFile, int nRank){
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