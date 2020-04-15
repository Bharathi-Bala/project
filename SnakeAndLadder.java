package com.snakeandladder;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class SnakeAndLadder {
    HashMap<Integer,Integer> ladder=new HashMap<Integer, Integer>();
    HashMap<Integer,Integer> snake=new HashMap<Integer, Integer>();
    int[][] board = new int[10][10];
    String[][] boardShown=new String[10][10];
    public  SnakeAndLadder(){
        ladder.put(1,38);
        ladder.put(4,14);
        ladder.put(9,31);
        ladder.put(21,42);
        ladder.put(28,84);
        ladder.put(51,67);
        ladder.put(71,91);
        ladder.put(80,100);

        snake.put(17,7);
        snake.put(54,34);
        snake.put(62,19);
        snake.put(64,60);
        snake.put(87,24);
        snake.put(93,73);
        snake.put(95,75);
        snake.put(98,79);

        int num = 100;
        int num1 = 1;

        for(int i=0;i<board.length;i+=2)
        {
            for(int j=0;j<board[i].length;j++)
            {
                board[i][j]=num;
                num--;
            }
            num-=10;
        }
        for(int i=9;i>0;i-=2)
        {
            for(int j=0;j<board[i].length;j++)
            {
                board[i][j]=num1;
                num1++;
            }
            num1+=10;
        }
    }
    Random die=new Random();
    public int[] rollTheDie(int player,int square){
        int position=square;
        int dieValue=die.nextInt(6)+1;
        int currentPosition=position+dieValue;
        int[] positionDieValue=new int[2];
        positionDieValue[0]=currentPosition;
        positionDieValue[1]=dieValue;
        System.out.printf("Player %d,On square %d, rolls a %d", player,position, dieValue);
        if (currentPosition > 100) {
            System.out.printf(" but cannot move since  your position becomes greater than 100....");
        } else {
            System.out.printf(" and moves to square %d ", currentPosition);
            if (currentPosition == 100) {
                return positionDieValue;
            }
            else if (ladder.containsKey(currentPosition)) {
                currentPosition = ladder.get(currentPosition);
                positionDieValue[0]=currentPosition;
                System.out.printf("Yay! Landed on a ladder. Climb up to %d.", currentPosition);
                if (currentPosition == 100) {
                    return positionDieValue;
                }
            } else if (snake.containsKey(currentPosition)) {
                currentPosition = snake.get(currentPosition);
                positionDieValue[0]=currentPosition;
                System.out.printf("Oops! Landed on a snake. Slither down to %d.", currentPosition);
            }
        }
        return positionDieValue;
    }
    public void playGame() {
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the no of players ?");
        int len=input.nextInt();
        int[] players = new int[len];
        while (true) {
            for (int i = 0; i < players.length; i++) {
                System.out.println();
                //System.out.println("Player "+(i+1)+"  Chance press any character to roll the die :");
                //String cont=input.next();
                int[] ns = rollTheDie(i + 1, players[i]);
                int position=ns[0];
                int dieVal=ns[1];
                if (position == 100) {
                    System.out.printf("\nPlayer %d wins!", i + 1);
                    return;
                }
                players[i] = position;
                if(dieVal==6) {

                    System.out.print(" Yeah, Rolled a 6 so roll again !");
                    i=i-1;
                }
                System.out.println();
            }
            showBoard(setboardShown(players));
        }
    }
    public String[][] setboardShown(int[] players){
        boardShown=new String[10][10];
        for(int i=0;i<players.length;i++){
            for(int j=0;j<board.length;j++){
                for(int k=0;k<board.length;k++){
                    if(players[i]==board[j][k]){
                        boardShown[j][k]="P"+(i+1);
                    }
                    else if(boardShown[j][k]==null){
                        boardShown[j][k]=Integer.toString(board[j][k]);
                    }

                }
            }
        }
        return boardShown;
    }
    public  void showBoard(String[][] board)
    {
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].startsWith("P")) {
                        System.out.print("\033[4m\033[1m" + board[i][j] + "\033[0m\t\t");
                } else {
                        System.out.print(board[i][j] + "\t\t");
                }
            }
            System.out.println("\n\n");
        }

    }
    public static void main(String[] args) {
        SnakeAndLadder game=new SnakeAndLadder();
        game.playGame();

    }
}
