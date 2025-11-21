import java.util.*;
import java.io.*;

class Question {
    private String prompt;
    private String[] options;
    private char correctAnswer;

    public Question(String prompt, String[] options, char correctAnswer) {
        this.prompt = prompt;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getPrompt() { return prompt; }
    public String[] getOptions() { return options; }
    public char getCorrectAnswer() { return correctAnswer; }
}

public class QuizApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = loadQuestions("question.txt");

        Collections.shuffle(questions);
        int score = 0;

        System.out.println("==========================================");
        System.out.println("     HIGH SCHOOL GENERAL KNOWLEDGE QUIZ");
        System.out.println("==========================================\n");

        for (Question q : questions) {
            System.out.println(q.getPrompt());
            for (String opt : q.getOptions()) {
                System.out.println(opt);
            }

            System.out.print("Your answer: ");
            char answer = Character.toUpperCase(sc.next().charAt(0));

            if (answer == q.getCorrectAnswer()) {
                System.out.println("✔ Correct!\n");
                score++;
            } else {
                System.out.println("✘ Wrong! Correct answer: " + q.getCorrectAnswer() + "\n");
            }
        }

        System.out.println("------------------------------------------");
        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + " / " + questions.size());
        System.out.println("------------------------------------------");

        sc.close();
    }

 
    public static List<Question> loadQuestions(String filename) {
        List<Question> list = new ArrayList<>();

        try {
            Scanner file = new Scanner(new File(filename));

            while (file.hasNextLine()) {
                String prompt = file.nextLine().trim();
                if (prompt.isEmpty()) continue;

                String optA = file.nextLine().trim();
                String optB = file.nextLine().trim();
                String optC = file.nextLine().trim();
                String optD = file.nextLine().trim();
                String correct = file.nextLine().trim();

                list.add(new Question(
                    prompt,
                    new String[]{optA, optB, optC, optD},
                    correct.charAt(0)
                ));

                if (file.hasNextLine()) file.nextLine(); // skip blank line
            }

            file.close();
        } catch (Exception e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }

        return list;
    }
}
