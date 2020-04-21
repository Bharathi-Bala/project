package com.snakeandladder;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class SnakeAndLadder{

    HashMap<Integer,Integer> ladder=new HashMap<Integer, Integer>();
    HashMap<Integer,Integer> snake=new HashMap<Integer, Integer>();
    int[][] board = new int[10][10];
    String[][] boardShown=new String[10][10];
    Players[] players;
    Random die=new Random();

    public  SnakeAndLadder(){
        setSnake();
        setLadder();
        setBoard();
        setPlayers();
    }

    public void setSnake(){
        snake.put(17,7);
        snake.put(54,34);
        snake.put(62,19);
        snake.put(64,60);
        snake.put(87,24);
        snake.put(93,73);
        snake.put(95,75);
        snake.put(98,79);
    }

    public void setLadder(){
        ladder.put(1,38);
        ladder.put(4,14);
        ladder.put(9,31);
        ladder.put(21,42);
        ladder.put(28,84);
        ladder.put(51,67);
        ladder.put(71,91);
        ladder.put(80,100);
    }

    public void setBoard(){
        int num = 100;
        int val=-1;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                board[i][j]=num;
                num=num+val;
            }
            num=num-val;
            num-=10;
            val=val*-1;
        }
    }
    public void setPlayers(){
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the no of players ?");
        int len=input.nextInt();
        players=new Players[len];
        for(int i=0;i<len;i++){
            System.out.println("Enter the  player"+(i+1)+" name.");
            String n=input.next();
            System.out.println(n+"  choose a color .");
            String c=input.next();
            Players p=new Players(n,c,0);
            players[i]=p;
        }
    }

    public void playGame() {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                System.out.println();
                //System.out.println("Player "+(i+1)+"  Chance press any character to roll the die :");
                //String cont=input.next();
                int[] ns = rollTheDie(players[i]);
                int position=ns[0];
                int dieVal=ns[1];
                if (position == 100) {
                    System.out.printf("\n"+players[i].name+" wins!");
                    return;
                }
                players[i].square = position;
                if(dieVal==6) {
                    System.out.print(" Yeah, Rolled a 6 so roll again !");
                    i=i-1;
                }
                System.out.println();
            }
            showBoard(setboardShown(players));
        }
    }

    public int[] rollTheDie(Players playr){
        String name=playr.name;
        int position=playr.square;

        int dieValue=die.nextInt(6)+1;

        int currentPosition=position+dieValue;

        int[] positionDieValue=new int[2];
        positionDieValue[0]=currentPosition;
        positionDieValue[1]=dieValue;

        System.out.printf(name +" ,On a square %d,rolls a %d",position,dieValue);
        if (currentPosition > 100) {
            currentPosition=position;
            positionDieValue[0]=currentPosition;
            System.out.printf(" but cannot move since  your position becomes greater than 100....");
            return positionDieValue;
        } else {
            System.out.printf(" and moves to square %d ", currentPosition);
            if (ladder.containsKey(currentPosition)) {
                currentPosition = ladder.get(currentPosition);
                positionDieValue[0]=currentPosition;
                System.out.printf("Yay! Landed on a ladder. Climb up to %d.", currentPosition);

            } else if (snake.containsKey(currentPosition)) {
                currentPosition = snake.get(currentPosition);
                positionDieValue[0]=currentPosition;
                System.out.printf("Oops! Landed on a snake. Slither down to %d.", currentPosition);

            }
            return positionDieValue;
        }
    }

    public String[][] setboardShown(Players[] players){
        boardShown=new String[10][10];
        for(int i=0;i<players.length;i++){

            for(int j=0;j<board.length;j++){
                for(int k=0;k<board.length;k++){
                    if(players[i].square==board[j][k]){
                        boardShown[j][k]= String.valueOf(players[i].name.charAt(0));
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

                System.out.print(board[i][j] + "\t\t");
            }
            System.out.println("\n\n");
        }
    }

    public static void main(String[] args) {
        SnakeAndLadder game=new SnakeAndLadder();
        game.playGame();


    }
}
class Players
{
    String name,color;
    int square;
    Players(String n,String c,int s){
        this.name=n;
        this.color=c;
        this.square=s;
    }
}
