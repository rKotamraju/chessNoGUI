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

        while(gameOn){ //can change to true actually
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
            } else if (move.equals("draw")){
                //work with draws here
            } else {

                //we are using 7 instead of 8 because if getValue returned a 0, we would go out of bounds
                String[] moves = move.split(" ");
                int oFile = getValue(moves[0].charAt(0));
                int oRank = 7-getValue(moves[0].charAt(1));
                int nFile = getValue(moves[1].charAt(0));
                int nRank = 7-getValue(moves[1].charAt(1));

                if(board[oRank][oFile] != null && (isWhiteTurn == board[oRank][oFile].isWhite)) {
                        if(board[oRank][oFile].move(moves[0], moves[1], board, oFile, oRank, nFile, nRank) == true){
                            System.out.println(board[oRank][oFile]);
                            board[nRank][nFile] = board[oRank][oFile];
                            board[oRank][oFile] = null;
                        }else{
                            System.out.println("MOVE IS INVALID");
                            continue; // this should act as a redo
                        }
                } else {
                    System.out.println("MOVE IS INVALID");
                    continue;
                }
            }

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

        return hashmap.get(c);
    }

    public static void setBoard(Piece[][] board){

        //["Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"]
        //Write this more efficiently

        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[0][1] = new Bishop(false);
        board[0][6] = new Bishop(false);
        board[0][2] = new Knight(false);
        board[0][5] = new Knight(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);

        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);
        board[7][1] = new Bishop(true);
        board[7][6] = new Bishop(true);
        board[7][2] = new Knight(true);
        board[7][5] = new Knight(true);
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

}
