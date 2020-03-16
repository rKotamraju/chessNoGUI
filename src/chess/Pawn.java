package chess;

public class Pawn extends Piece{

    public Pawn(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wp" : "bp";
    }

    @Override
    public boolean move(String omove, String nmove) {

        System.out.println("Player is moving a Pawn");

        int oldRank = Integer.parseInt(String.valueOf(omove.charAt(1)));
        char oldFile = omove.charAt(0);

        int newRank = Integer.parseInt(String.valueOf(nmove.charAt(1)));
        char newFile = nmove.charAt(0);

        /*System.out.println(oldFile);
        System.out.println(oldRank);
        System.out.println(newFile);
        System.out.println(newRank);*/

        if(oldFile != newFile){
            //System.out.println("Invalid Move");
            return false;
        }
        else{
            if(newRank-oldRank == 2){
                if(hasMoved == false){
                    hasMoved = true;
                    return true;

                }else{
                    //System.out.println("invalid move");
                    return false;
                }
            }

            else if(newRank-oldRank == 1){
                hasMoved = true;
                return true;
            }
            else{
               // System.out.println("invalid move");
                return false;
            }
        }
    }
}
