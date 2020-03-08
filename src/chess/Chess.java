package chess;

import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        boolean gameOn = true;
        boolean isWhiteTurn = true;
        Scanner in = new Scanner(System.in);
        String move;

        while(gameOn){

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

}
