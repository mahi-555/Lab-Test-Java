import java.io.*;
import java.util.*;

public class LabTest2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking player info
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter difficulty level (easy/medium/hard): ");
        String level = sc.nextLine().toLowerCase();

        int score = 0;

        // Reading questions from a file
        try {
            BufferedReader br = new BufferedReader(new FileReader("questions.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                String op = parts[1];

                // SKIP questions not allowed for this difficulty
                if (!isAllowed(op, level)) {
                    continue;
                }

                // Ask the question
                System.out.print(line + " = ");
                int correctAnswer = evaluate(line);
                int userAnswer = sc.nextInt();

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! Correct answer: " + correctAnswer);
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error reading questions file!");
            return;
        }

        // Save result
        try {
            FileWriter fw = new FileWriter("scores.txt", true);
            fw.write("Player: " + name + ", Level: " + level + ", Score: " + score + "\n");
            fw.close();
            System.out.println("Your score has been saved!");
        } catch (Exception e) {
            System.out.println("Error saving score!");
        }
    }

    // Function to evaluate the arithmetic expression
    public static int evaluate(String expr) {
        String[] parts = expr.split(" ");

        int a = Integer.parseInt(parts[0]);
        String op = parts[1];
        int b = Integer.parseInt(parts[2]);

        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }
        return 0;
    }

    // Difficulty filtering
    public static boolean isAllowed(String op, String level) {
        switch (level) {
            case "easy":
                return op.equals("+") || op.equals("-");

            case "medium":
                return op.equals("+") || op.equals("-") || op.equals("*");

            case "hard":
                return true; // all operators allowed

            default:
                return true; // fallback
        }
    }
}