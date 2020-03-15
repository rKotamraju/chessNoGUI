package chess;

import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        boolean gameOn = true;
        boolean isWhiteTurn = true;
        Scanner in = new Scanner(System.in);
        String move;

        Piece[][] board = new Piece[8][8];

        setBoard(board);

        while(gameOn){

            printBoard(board);
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
            }
            isWhiteTurn = !(isWhiteTurn);
        }

    }

    public static void setBoard(Piece[][] board){

        //["Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"]

        for(int r = 0; r<board.length; r++){
            for(int c = 0; c<(board[r].length)/2; c++){
                if(r == 1) {
                    board[r][c] = new Pawn(false);
                    board[7-r][7-c] = new Pawn(false);
                }
                if(r == 6){
                    board[r][c] = new Pawn(true);
                    board[7-r][7-c] = new Pawn(true);
                }
            }
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
        System.out.println();

    }

}
