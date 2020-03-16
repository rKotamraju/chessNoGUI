package chess;

public class Bishop extends Piece{

    public Bishop(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wB" : "bB";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int nFile, int nRank) {
        System.out.println("Player is moving a Bishop");

        int oldRank = Integer.parseInt(String.valueOf(omove.charAt(1)));
        char oldFile = omove.charAt(0);

        int newRank = Integer.parseInt(String.valueOf(nmove.charAt(1)));
        char newFile = nmove.charAt(0);

        return false;
    }
}
