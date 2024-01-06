
import java.util.Random;
import java.util.Scanner;

public class TextBasedGame {
    public static int PATH_LENGTH = 100;
    public static int MAX_STEPS = 10;
    public static String[] MONSTERS = {"+", "-", "*", "/", "%", " "};
    public String[] path = new String[PATH_LENGTH];
    public int playerScore = 100;
    public int currentPosition = 0;

    // 1. Call the game in the main method
    public static void main(String[] args) {
        TextBasedGame game = new TextBasedGame();
        game.initializePath();
        game.startGame();
    }

    // 2. Generate random monsters for each tile
    public void initializePath() {
        Random random = new Random();
        for (int i = 0; i < PATH_LENGTH; i++) {
            path[i] = MONSTERS[random.nextInt(MONSTERS.length)];
        }
    }

    // 3. Start the game!
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        while (currentPosition < PATH_LENGTH) {
            int steps = promptForSteps(scanner);

            if (!isValidStepCount(steps)) {
                System.out.println("Invalid number of steps. You can only move 1-10 steps at a time.");
                continue;
            }

            movePlayer(steps);

            if (currentPosition >= PATH_LENGTH) {
                break;
            }

            String currentMonster = path[currentPosition];
            System.out.println("You landed on a tile with a " + currentMonster + " monster!");
            applyMonsterEffect(currentMonster);
            printScore();
        }

        scanner.close();
        endGame();
    }

    // 4. Print welcome message
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Trail of Mathemagics!");
        System.out.println("Your journey begins with a score of 100. Survive the trail and finish with more than your starting score to win.");
    }

    // 5. Prompt the user for the number of steps to move
    public static int promptForSteps(Scanner scanner) {
        System.out.print("\nChoose your steps (1-10): ");
        return scanner.nextInt();
    }

    // 6. Check if step count is valid (has to be between 1-10)
    public static boolean isValidStepCount(int steps) {
        return steps >= 1 && steps <= MAX_STEPS;
    }

    // 7. Move the player on the path
    public void movePlayer(int steps) {
        currentPosition += steps;
    }

    // 8. Apply the effect of the monster (various arithmetic operators)
    public void applyMonsterEffect(String monster) {
        if (monster.equals("+")) {
            playerScore += 50;
        } else if (monster.equals("-")) {
            playerScore -= 20;
        } else if (monster.equals("*")) {
            playerScore *= 2;
        } else if (monster.equals("/")) {
            if (playerScore != 0) playerScore /= 2;
        } else if (monster.equals("%")) {
            playerScore %= 150;
        }
    }

    // 9. Print the current score
    public void printScore() {
        System.out.println("Your score is now: " + playerScore);
    }

    // 10. End the game and print the final message
    public void endGame() {
        System.out.println("Your journey on the Trail of Mathemagics ends here.");
        if (playerScore > 100) {
            System.out.println("Congratulations! You've won with a score of " + playerScore + "!");
        } else if (playerScore == 100) {
            System.out.println("It's a tie! You finished with a score of 100.");
        } else {
            System.out.println("Alas, you've lost. Your final score is " + playerScore + ".");
        }
    }
}
