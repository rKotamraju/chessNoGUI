package chess;

import java.util.HashMap;
import java.util.Scanner;

public class Chess {

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
        boolean prevDraw = false;


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
                gameOn = false;
                break; //-- gameOver;
            }
            else {


                if(prevDraw == true){
                    if(move.equals("draw")){
                        gameOn = false;
                        System.out.println("draw");
                        break; //game over
                    }

                    prevDraw = false;
                }

                //we are using 7 instead of 8 because if getValue returned a 0, we would go out of bounds
                String[] moves = move.split(" ");
                int oFile = getValue(moves[0].charAt(0));
                int oRank = 7-getValue(moves[0].charAt(1));
                int nFile = getValue(moves[1].charAt(0));
                int nRank = 7-getValue(moves[1].charAt(1));

                Piece newPosition = board[nRank][nFile];

                if(move.substring(move.lastIndexOf(" ")+1).equals("draw?")){
                    prevDraw = true;
                }

                if(oFile == -1 || oRank == 8 || nFile == -1 || nRank == 8){ //we set Ranks to 8 bc lines 45 and 47
                    System.out.println("MOVE IS OUT OF BOUNDS");
                    continue;
                }


                if(board[oRank][oFile] != null && (isWhiteTurn == board[oRank][oFile].isWhite)) {
                    // System.out.println("Moving from " + oFile + " " + oRank + " to " + nFile + " " + nRank);
                    if(board[oRank][oFile].move(board, oFile, oRank, nFile, nRank) == true){



                        //checking for pawn promotion
                        if(checkPawnPromotion(board, oRank, oFile, nRank, nFile) == true){
                            System.out.println("CHECK PAWN PROMOTION IS TRUE");
                            promotePawn(board, move.charAt(move.length()-1), oRank, oFile, nRank, nFile);

                        }

                        System.out.println(board[oRank][oFile]);

                        board[nRank][nFile] = board[oRank][oFile];
                        board[oRank][oFile] = null;

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
                    System.out.println("WE ARE ON LINE 104");
                    System.out.println("MOVE IS INVALID");
                    continue;
                }

                //invalid
                if(check(board, isWhiteTurn ? whiteKing : blackKing)){
                    //printBoard(board);
                    System.out.println("WE ARE ON LINE 109 MOVE IS INVALID");
                    reverseMove(board, oFile, oRank, nFile, nRank, newPosition);
                    continue;
                }

                //opposite
                check = check(board, isWhiteTurn ? blackKing : whiteKing);

                if(check){
                    System.out.println("running");
                    if(checkmate(board, isWhiteTurn ? blackKing : whiteKing)){
                        System.out.println("CHECKMATE");
                        System.out.println(isWhiteTurn ? "White Wins" : "Black Wins");
                        break;
                    }

                }

            }

            System.out.println("BlackKing Position File "+blackKing.file+" Rank: "+blackKing.rank);
            System.out.println("WhiteKing Position File "+whiteKing.file+" Rank: "+whiteKing.rank);

            //end turn code
            System.out.println();
            printBoard(board);
            isWhiteTurn = !(isWhiteTurn);
        }

    } //end of the main

    public static void reverseMove(Piece[][] board, int oFile, int oRank, int nFile, int nRank, Piece newPosition){
        System.out.println("Reversing move "+board[nRank][nFile]);
        board[oRank][oFile] = board[nRank][nFile];
        board[nRank][nFile] = newPosition;

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

        Piece temp = board[king.rank][king.file];
        board[king.rank][king.file] = null;

        System.out.println("Starting check");
        for(int r = 0; r<board.length; r++){
            for(int f = 0; f<board[r].length; f++){
                if((board[r][f] != null)  && (board[r][f].isWhite != king.isWhite)){
                    if((board[r][f].move(board, f, r, king.file, king.rank) == true) ){
                        System.out.println(board[r][f]+" this piece is can kill the king");
                        System.out.println("King's File: "+king.file);
                        System.out.println("King's Rank: "+king.rank);
                        board[king.rank][king.file] = temp;
                        return true;
                    }
                }
            }
        }

        board[king.rank][king.file] = temp;
        return false;
    }

    public static boolean checkmate(Piece[][] board, Piece king){
        System.out.println("Call for checkmate");
        //every move for the king is false

        int ogRank = king.rank;
        int ogFile = king.file;

        for(int r = king.rank-1; r<=king.rank+1; r++){
            if(r < 0 || r > 7 ){ //skip checking if out of bounds
                continue;
            }
            for(int f = king.file-1; f<=king.file+1; f++){

                if(f < 0 || f > 7){ //skip checking if out of bounds
                    continue;
                }
                if(r == king.rank && f == king.file){ //do not check the king's spot
                    continue;
                }

                System.out.println("TRYING TO MOVE KING");
                System.out.println("File: "+f+" Rank:"+r +" THIS IS THE POSITION BEING CHECKED DURING CHECKMATE");
                System.out.println("File: "+king.file+" Rank: "+king.rank+" POSITION OF KING BEFORE RESETTING IN CHECKMATE METHOD");
                if(king.move(board, king.file, king.rank, f, r)){
                    //System.out.println("File: "+king.file+" Rank: "+king.rank+" POSITION OF KING WHEN RETURNING FROM CHECKMATE METHOD");
                    king.file = ogFile;
                    king.rank = ogRank;
                    //king.move(board, r, f, king.file, king.rank);
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkPawnPromotion(Piece[][] board, int oRank, int oFile, int nRank, int nFile){
        if(!(board[oRank][oFile] instanceof Pawn)){ //has to be a pawn
            System.out.println("NOT AN INSTANCE OF PAWN");
            return false;
        }

        if((board[oRank][oFile].isWhite) && (nRank != 0)){
            System.out.println("NOT ON RIGHT SIDE OF BOARD White,nRank: " + nRank);
            return false;
        }
        else if(!(board[oRank][oFile].isWhite) && (nRank != 7)){
            System.out.println("NOT ON RIGHT SIDE OF BOARD BLACK");
            return false;
        }
        return true;
    }

    public static void promotePawn(Piece[][] board, char promotion, int oRank, int oFile, int nRank, int nFile){
        boolean white = board[oRank][oFile].isWhite;
        if(promotion == 'Q'){
            board[oRank][oFile] = new Queen(white);
        }
        else if(promotion == 'N'){
            board[oRank][oFile] = new Knight(white);
        }
        else if(promotion == 'B'){
            board[oRank][oFile] = new Bishop(white);

        }
        else if(promotion == 'R'){
            board[oRank][oFile] = new Rook(white);
        }
        else{ //not specified
            board[oRank][oFile] = new Queen(white);
        }

    }



}
