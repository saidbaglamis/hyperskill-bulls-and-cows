package bullscows;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int bulls = 0;
    static int cows = 0;
    static String input;
    static String[] inputs;
    static final int MAX_LENGTH = 10;
    static int length;
    static Integer[] intArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static List<Integer> intList;
    static String secretCode = ""; //3 6 0 1
    static int turn = 1;
    static int bullDuplicateCheck = 0;


    public static void main(String[] args) {
        /*System.out.println("""
                The secret code is prepared: ****.
                                
                Turn 1. Answer:
                1234
                Grade: 1 cow.
                                
                Turn 2. Answer:
                5678
                Grade: 1 cow.
                                
                Turn 3. Answer:
                9012
                Grade: 1 bull and 1 cow.
                                
                Turn 4. Answer:
                9087
                Grade: 1 bull and 1 cow.
                                
                Turn 5. Answer:
                1087
                Grade: 1 cow.
                                
                Turn 6. Answer:
                9205
                Grade: 3 bulls.
                                
                Turn 7. Answer:
                9305
                Grade: 4 bulls.
                Congrats! The secret code is 9305.
                """);
                */
        SecretCodeLengthPrompt();
        SecretCodeGenerator();
        //System.out.printf("%s  %d", secretCode, secretCode.length());
        while (true) {
            System.out.printf("Turn %d:\n", turn);
            GetInput();
            Grader();
            Status();
            EndCheck();
            UpdateTurnNumber();
        }

    }

    public static void GetInput() {
        input = scanner.next();
        inputs = input.split("(?<=.)");
    }
    public static void UpdateTurnNumber() {
        turn++;
    }

    public static void SecretCodeGenerator() {
       intList = Arrays.asList(intArray);
       RandomNumberGenerator();
       for (int i = 0; i < length; i++) {
           secretCode += intList.get(i).toString();
       }
    }

    public static void EndCheck() {
        if (bulls == length) {
            System.out.println("Congratulations! You guessed the secret code.");
            System.exit(0);
        }
    }

    public static void SecretCodeLengthPrompt() {
        System.out.println("Please, enter the secret code's length:");
        while (true) {
            length = scanner.nextInt();
            if (length <= MAX_LENGTH) {
                break;
            } else {
                System.out.printf("Error: can't generate a secret number with a length of %d. Please input maximum 10.", length);
            }
        }
        System.out.println("Okay, let's start a game!");
    }

    public static void Status() {
        if (bulls != 0 && cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
        } else if (bulls != 0) {
            System.out.printf("Grade: %d bull(s)\n", bulls);
        } else if (cows != 0) {
            System.out.printf("Grade: %d cow(s)\n", cows);
        } else {
            System.out.printf("Grade: None\n");
        }
    }

    public static void RandomNumberGenerator() {
        while (true) {
            if (intList.get(0) == 0) {
                Collections.shuffle(intList);
            } else {
                break;
            }
        }
    }

    public static void LengthCheck() {
        if (length <= MAX_LENGTH) {
            System.out.printf("The random secret number is ");
            for (int i = 0; i < length; i++) {
                System.out.print(intList.get(i));
            }
        } else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        }
    }

    public static void Grader() {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < length; i++) {
            if (secretCode.contains(inputs[i])) {
                if (secretCode.charAt(i) == input.charAt(i)) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
    }

}
