package com.snakeandladder;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class SnakeAndLadder {
    HashMap<Integer,Integer> ladder=new HashMap<Integer, Integer>();
    HashMap<Integer,Integer> snake=new HashMap<Integer, Integer>();
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
        }
    }
    public static void main(String[] args) {
        SnakeAndLadder game=new SnakeAndLadder();
        game.playGame();

    }
}
