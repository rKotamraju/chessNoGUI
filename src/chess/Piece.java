package chess;

public abstract class Piece {
    char file;
    char rank;
    boolean isWhite;
    String name;
    boolean hasMoved;

    char[] orderOfRanks = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public abstract boolean move(String omove, String nmove, Piece[][] board, int oFile, int oRank, int nFile, int nRank);
    //had to add board, nFile, and nRank so you can check if the places you are moving to are null or not. Can't put in main
    // because different for each piece, easier and less headache to pass it in

    public String toString(){
        return name;
    }

}
