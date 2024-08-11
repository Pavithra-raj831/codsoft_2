import java.util.Random;
import java.util.Scanner;

public class RandomnumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuePlaying = true;
        int totalGamesPlayed = 0;
        int totalGamesWon = 0;
        int totalAttempts = 0;

        while (continuePlaying) {
            totalGamesPlayed++;
            int attemptsMade = 0;
            boolean gameResult = playGame(scanner, attemptsMade);
            if (gameResult) {
                totalGamesWon++;
            }
            totalAttempts += attemptsMade;
            continuePlaying = askToPlayAgain(scanner);
        }

        scanner.close();
        displayGameSummary(totalGamesPlayed, totalGamesWon, totalAttempts);
    }

    private static boolean playGame(Scanner scanner, int attemptsMade) {
        int upperLimit = 100;  // Fixed range for simplicity
        Random random = new Random();
        int numberToGuess = random.nextInt(upperLimit) + 1;
        int maxAttemptsAllowed = 10;

        System.out.println("I have generated a number between 1 and " + upperLimit + ".");
        System.out.println("You have " + maxAttemptsAllowed + " attempts to guess the number.");

        while (attemptsMade < maxAttemptsAllowed) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attemptsMade++;

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You've guessed the correct number.");
                return true;
            } else {
                giveHint(userGuess, numberToGuess);
            }
        }

        System.out.println("You've used all your attempts. The correct number was " + numberToGuess);
        return false;
    }

    private static void giveHint(int userGuess, int numberToGuess) {
        if (userGuess < numberToGuess) {
            System.out.println("Too low! Try again.");
        } else {
            System.out.println("Too high! Try again.");
        }

        if (numberToGuess % 2 == 0) {
            System.out.println("Hint: The number is even.");
        } else {
            System.out.println("Hint: The number is odd.");
        }
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (YES/NO): ");
        String userResponse = scanner.next();
        return userResponse.equalsIgnoreCase("YES");
    }

    private static void displayGameSummary(int totalGamesPlayed, int totalGamesWon, int totalAttempts) {
        System.out.println("Thanks for playing!");
        System.out.println("You played " + totalGamesPlayed + " games and won " + totalGamesWon + " of them.");
        System.out.println("Your total attempts were " + totalAttempts + ".");
    }
}
