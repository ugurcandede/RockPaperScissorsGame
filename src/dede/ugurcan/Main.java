package dede.ugurcan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char myMove;
        int turn, count = 1,
                computerScore = 0, myScore = 0;

        RPSTypes computer = RPSTypes.ROCK,
                myChoice = RPSTypes.ROCK;

        Scanner in = new Scanner(System.in);

        System.out.println("""
                                
                Hey, let's play Rock, Paper, Scissors! Please enter a move.
                        Rock = R, Paper= P, and Scissors = S.
                To quit the game, enter quit.""");

        System.out.println("\nHow many rounds would you like to play?");

        try {
            turn = Integer.parseInt(in.next("[0-9]+"));

            while (count <= turn) {
                System.out.println("\nROUND: " + count);
                System.out.println("What's your move? To make a move, enter rock, paper, or scissors.");

                int rand = (int) (Math.random() * 3);
                computer = getComputerChoice(computer, rand);

                myMove = in.next("[a-zA-Z]+").toUpperCase().charAt(0);

                if (quitGame(myMove)) break;

                myChoice = getMyChoice(myMove, myChoice);

                if (myChoice.equals(RPSTypes.SCISSORS) && computer.equals(RPSTypes.ROCK)
                        || myChoice.equals(RPSTypes.PAPER) && computer.equals(RPSTypes.SCISSORS)
                        || myChoice.equals(RPSTypes.ROCK) && computer.equals(RPSTypes.PAPER)) {
                    System.out.println("LOST");
                    computerScore++;
                    count++;
                } else if (myChoice.equals(RPSTypes.ROCK) && computer.equals(RPSTypes.SCISSORS)
                        || myChoice.equals(RPSTypes.SCISSORS) && computer.equals(RPSTypes.PAPER)
                        || myChoice.equals(RPSTypes.PAPER) && computer.equals(RPSTypes.ROCK)) {
                    System.out.println("WIN!");
                    myScore++;
                    count++;
                } else System.out.println("TIE");

            }

            gameOver(computerScore, myScore);

        } catch (InputMismatchException e) {
            System.err.println("\nPlease sure your choice is number\n");
        } catch (Exception e) {
            System.err.println("\nSomething Went Wrong\n");
            e.printStackTrace();
        }
    }

    private static void gameOver(int computerScore, int myScore) {
        System.out.printf("\n\nUSER: %d | COMPUTER: %d\n", myScore, computerScore);
        System.err.print("\nGAME OVER");

        if (computerScore > myScore) System.out.println("COMPUTER WIN!");
        else if (myScore > computerScore) System.out.println("YOU WIN");
        else System.out.println("TIE");
    }

    private static boolean quitGame(char myMove) {
        if (myMove == 'Q') {
            System.out.println("Thanks for playing Rock, Paper, Scissors!");
            return true;
        }
        return false;
    }

    private static RPSTypes getMyChoice(char myMove, RPSTypes myChoice) {
        switch (myMove) {
            case 'R' -> myChoice = RPSTypes.ROCK;
            case 'P' -> myChoice = RPSTypes.PAPER;
            case 'S' -> myChoice = RPSTypes.SCISSORS;
            default -> System.err.println("Invalid Choice");
        }
        return myChoice;
    }

    private static RPSTypes getComputerChoice(RPSTypes computer, int rand) {
        switch (rand) {
            case 0 -> computer = RPSTypes.ROCK;
            case 1 -> computer = RPSTypes.PAPER;
            case 2 -> computer = RPSTypes.SCISSORS;
        }
        return computer;
    }

}
