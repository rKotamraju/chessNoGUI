package chess;

import java.util.HashMap;
import java.util.Scanner;

public class Chess {

    /*

        1. have global variables for the kings positions
        2. use the fields in the king class -- have non-static setKingPosition method
        3. Have whiteKing and blackKing as local variables in main method

        int wKRank
        int wKFile

        int bKRank
        int
     */
    public static void main(String[] args) {

        boolean gameOn = true;
        boolean isWhiteTurn = true;
        Scanner in = new Scanner(System.in);
        String move;

        Piece[][] board = new Piece[8][8];

        setBoard(board);
        printBoard(board);

        Piece whiteKing = board[7][4];
        Piece blackKing = board[0][4];
        boolean check = false;

        while(gameOn){ //can change to true actually
            if(check){
                System.out.println("CHECK");
            }
            if(isWhiteTurn){
                System.out.print("White's Move: ");
                move = in.nextLine();
            } else{
                System.out.print("Black's Move: ");
                move = in.nextLine();
            }

            if(move.equals("resign")){
                if(isWhiteTurn){
                    System.out.println("Black Wins");
                } else {
                    System.out.println("White Wins");
                }
                //gameOn = false;
                break; //-- gameOver;
            } else if(move.substring(move.lastIndexOf(" ")+1).equals("draw?")){
                    if(isWhiteTurn){
                        System.out.print("Black's Move: ");
                    }
                    else{
                        System.out.print("White's Move: ");
                    }
                    move = in.nextLine();
                    if(move.equals("draw")){
                        break; //game over
                    }
            } else {

                //we are using 7 instead of 8 because if getValue returned a 0, we would go out of bounds
                String[] moves = move.split(" ");
                int oFile = getValue(moves[0].charAt(0));
                int oRank = 7-getValue(moves[0].charAt(1));
                int nFile = getValue(moves[1].charAt(0));
                int nRank = 7-getValue(moves[1].charAt(1));

                if(oFile == -1 || oRank == 8 || nFile == -1 || nRank == 8){ //we set Ranks to 8 bc lines 45 and 47
                    System.out.println("MOVE IS OUT OF BOUNDS");
                    continue;
                }

                if(board[oRank][oFile] != null && (isWhiteTurn == board[oRank][oFile].isWhite)) {
                   // System.out.println("Moving from " + oFile + " " + oRank + " to " + nFile + " " + nRank);
                        if(board[oRank][oFile].move(board, oFile, oRank, nFile, nRank) == true){
                            System.out.println(board[oRank][oFile]);
                            board[nRank][nFile] = board[oRank][oFile];
                            board[oRank][oFile] = null;

                            System.out.println("printing something");

                            if(board[nRank][nFile] instanceof King){
                                if(isWhiteTurn){
                                    whiteKing = board[nRank][nFile];
                                } else{
                                    blackKing = board[nRank][nFile];
                                }
                            }

                        }else{
                            System.out.println("MOVE IS INVALID");
                            continue; // this should act as a redo
                        }
                } else {
                    System.out.println("MOVE IS INVALID");
                    continue;
                }

                //invalid
                if(check(board, isWhiteTurn ? whiteKing : blackKing)){
                    System.out.println("MOVE IS INVALID");
                    continue;
                }

                //opposite
                check = check(board, isWhiteTurn ? blackKing : whiteKing);

            }


            System.out.println(whiteKing);
            System.out.println(blackKing);

            System.out.println("BlackKing Position File "+blackKing.file+" Rank: "+blackKing.rank);
            System.out.println("WhiteKing Position File "+whiteKing.file+" Rank: "+whiteKing.rank);

            //end turn code
            System.out.println();
            printBoard(board);
            isWhiteTurn = !(isWhiteTurn);
        }

    }

    public static int getValue(char c){
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        hashmap.put('a', 0); hashmap.put('1', 0);
        hashmap.put('b', 1); hashmap.put('2', 1);
        hashmap.put('c', 2); hashmap.put('3', 2);
        hashmap.put('d', 3); hashmap.put('4', 3);
        hashmap.put('e', 4); hashmap.put('5', 4);
        hashmap.put('f', 5); hashmap.put('6', 5);
        hashmap.put('g', 6); hashmap.put('7', 6);
        hashmap.put('h', 7); hashmap.put('8', 7);

        try {
            return hashmap.get(c);
        } catch (NullPointerException e){
            return -1;
        }
    }

    public static void setBoard(Piece[][] board){

        //["Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"]
        //Write this more efficiently

        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);

        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);


        for(int c = 0; c<(board[0].length); c++){
                board[1][c] = new Pawn(false);
                board[6][c] = new Pawn(true);
        }

    }

    public static void printBoard(Piece[][] board){
        for(int r = 0; r<board.length; r++){
            for(int c = 0; c<board[r].length; c++){
                if(board[r][c] == null){
                    if ((r % 2 == 0 && c % 2 == 0) || (r % 2 != 0 && c % 2 != 0)) { //even even, or odd odd
                        System.out.print("   ");
                    }else{
                        System.out.print("## ");
                    }
                }else{
                    System.out.print(board[r][c]+" ");
                }
            }
            System.out.println(8-r);
        }

        //using ascii values to print files, 97 = a, 104=h
        for(int i = 0; i<8; i++){
            System.out.print(" "+(char)(97+i)+" ");
        }
        System.out.println("\n");

    }

    public static boolean check(Piece[][] board, Piece king){
        //alt. make it a int method 1 white is on check, -1 black is on check, 0 neither are on check
        /*
            1. Before -- prevention bc otherwise invalid move
            2. After -- warning to the other player
         */
//        if(board[rank][file].isWhite == king.isWhite){
//            //you are putting your king in check -- INVALID MOVE
//        }

        for(int r = 0; r<board.length; r++){
            for(int f = 0; f<board[r].length; f++){
                if((board[r][f] != null) && (board[r][f].move(board, f, r, king.file, king.rank) == true)){
                    System.out.println(board[r][f]);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkmate(){

        //every move is false

        return false;
    }

}
