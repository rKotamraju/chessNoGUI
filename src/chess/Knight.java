package chess;

public class Knight extends Piece{

    public Knight(boolean isWhite){
        this.isWhite = isWhite;
        this.name = isWhite ? "wK" : "bK";
    }

    @Override
    public boolean move(String omove, String nmove, Piece[][] board, int nFile, int nRank) {
        System.out.println("Player is moving a Knight");

        int oldRank = Integer.parseInt(String.valueOf(omove.charAt(1)));
        char oldFile = omove.charAt(0);

        int newRank = Integer.parseInt(String.valueOf(nmove.charAt(1)));
        char newFile = nmove.charAt(0);

        return false;
    }
}