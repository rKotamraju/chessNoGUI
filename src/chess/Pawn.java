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
        System.out.println("Moving from " + oFile + " " + oldRank + " to " + nFile + " " + newRank);

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
                if(checkColor(board, oldRank, newRank, nFile)==true){
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
            if(checkColor(board, oldRank, newRank, nFile) == true){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public boolean checkColor(Piece[][] board, int oldRank, int newRank, int nFile){ //cannot use Math.abs because then you could move backwards
        if(isWhite == true){
            if(newRank-oldRank == 2){
                if(hasMoved == false){
                    if(board[(newRank+1)][nFile] != null){ //if there is something in the way
                        System.out.println("can't jump");
                        return false;
                    }
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
                    /*if(board[(oldRank-1)][nFile] != null){ //if there is something in the way
                        System.out.println("can't jump");
                        return false;
                    }*/
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