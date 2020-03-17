package chess;

public class Pawn extends Piece{

    public Pawn(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wp" : "bp";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank) {

        System.out.println("Player is moving a Pawn");

        int oldRank = Integer.parseInt(String.valueOf(omove.charAt(1)));
        char oldFile = omove.charAt(0);

        int newRank = Integer.parseInt(String.valueOf(nmove.charAt(1)));
        char newFile = nmove.charAt(0);

        if(oldFile != newFile){ //checks if you are killing another piece
            if(board[nRank][nFile] == null){ //cannot go diagonal if there is nothing to kill
                return false;
            }

            int i;
            for(i = 0; i < orderOfRanks.length;i++){
                if(orderOfRanks[i] == oldFile){
                    break;
                }
            }

            if(newFile == orderOfRanks[i+1] || newFile == orderOfRanks[i-1]){
                    if(checkColor(oldRank, newRank)==true){
                        return true;
                    }
                    else{
                        return false;
                    }
            }
            else{
                return false;
            }

        } else{ //normal move, not killing
            if(board[nRank][nFile] != null){
                return false;
            }
            if(checkColor(oldRank, newRank) == true){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public boolean checkColor(int oldRank, int newRank){ //cannot use Math.abs because then you could move backwards
        if(isWhite == true){
            if(newRank-oldRank == 2){
                if(hasMoved == false){
                    hasMoved = true;
                    return true;

                }else{
                    //System.out.println("invalid move");
                    return false;
                }
            } else if(newRank-oldRank == 1){
                hasMoved = true;
                return true;
            } else{
                // System.out.println("invalid move");
                return false;
            }
        } else{ //if black
            if(newRank-oldRank == -2){
                if(hasMoved == false){
                    hasMoved = true;
                    return true;

                }else{
                    //System.out.println("invalid move");
                    return false;
                }
            } else if(newRank-oldRank == -1){
                hasMoved = true;
                return true;
            } else{
                // System.out.println("invalid move");
                return false;
            }
        }
    }
}
