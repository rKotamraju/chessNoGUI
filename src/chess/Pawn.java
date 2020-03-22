package chess;

public class Pawn extends Piece{

    public Pawn(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wp" : "bp";
    }

    @Override
    public boolean move(Piece[][] board, int oFile, int oRank, int nFile, int nRank) {

        //System.out.println("Player is moving a Pawn");

        if(oFile == nFile){ //normal move, not killing anyone
                if(board[nRank][nFile]!=null){
                    System.out.println("Cant move to a taken spot!");
                    return false; //trying to move to an occupied space
                }

                System.out.println("Has made it to checkMove");
                if(checkMove(board, oFile, oRank, nFile, nRank)){
                    return true;
                }else{
                    return false;
                }
        }

        else{ //killing, moving diagonal
            if(board[nRank][nFile] == null){
                System.out.println("Cannot kill nothing!");
                return false;
            }
            //check that you are killing opposite color
            if((isWhite && board[nRank][nFile].isWhite) || ((!isWhite) && !(board[nRank][nFile].isWhite))){
                System.out.println("Dont kill your friend!");
                return false;
            }
            if(isWhite && (nRank-oRank == -1) && (Math.abs(nFile-oFile) == 1)){
                hasMoved = true;
                return true;
            }
            else if(!isWhite && (nRank-oRank == 1) && (Math.abs(nFile-oFile)==1)){
                hasMoved=true;
                return true;
            }
            else{
                return false;
            }
        }
    }

    public boolean checkMove (Piece[][] board, int oFile, int oRank, int nFile, int nRank){
        if(isWhite){
            //check if one space

            if((nRank - oRank) == -1){
                hasMoved = true;
                return true;
            }
            //check if two space
            else if((nRank-oRank) == -2){
                if(hasMoved == true){
                    System.out.println("Not first move! Cannot go two spaces");
                    return false;
                }
                //check middle space

                if(board[nRank+1][oFile]==null){
                    hasMoved = true;
                    return true;
                }
                else{
                    System.out.println("Cannot jump over");
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{ //Black
            //check if 1 space
            if((nRank - oRank) == 1){
                hasMoved = true;
                return true;
            }
            //check if 2 spaces
            if((nRank - oRank) == 2){
                if(hasMoved == true){
                    System.out.println("Cannot move two spaces if not first turn");
                    return false;
                }
                if(board[nRank-1][oFile] == null){
                    hasMoved = true;
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }

        }
    }

}