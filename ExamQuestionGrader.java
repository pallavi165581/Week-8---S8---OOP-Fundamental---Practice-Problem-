import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Problem 4 - Examination Question Grader
// Each question type overrides grade(); essays do keyword matching.
public class ExamQuestionGrader {

    static abstract class Question {
        protected String type;
        protected String correctAnswer;
        protected String studentAnswer;
        protected int points;

        Question(String type, String correctAnswer, String studentAnswer, int points) {
            this.type = type;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        abstract double grade();
    }

    static class McqQuestion extends Question {
        McqQuestion(String correct, String student, int points) {
            super("MCQ", correct, student, points);
        }
        double grade() {
            return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0;
        }
    }

    static class TrueFalseQuestion extends Question {
        TrueFalseQuestion(String correct, String student, int points) {
            super("TF", correct, student, points);
        }
        double grade() {
            return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0;
        }
    }

    static class EssayQuestion extends Question {
        EssayQuestion(String correct, String student, int points) {
            super("ESSAY", correct, student, points);
        }
        double grade() {
            // count how many of the comma-separated keywords appear in the answer
            String[] keywords = correctAnswer.split(",");
            int found = 0;
            for (String keyword : keywords) {
                if (studentAnswer.toLowerCase().contains(keyword.trim().toLowerCase())) {
                    found++;
                }
            }
            if (found >= 2) {
                return points * 0.75;
            } else if (found == 1) {
                return points * 0.50;
            }
            return 0;
        }
    }

    // splits a line into tokens, keeping "quoted text" together as one token
    static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '"') {
                int end = line.indexOf('"', i + 1);
                tokens.add(line.substring(i + 1, end));
                i = end + 1;
            } else if (line.charAt(i) == ' ') {
                i++;
            } else {
                int end = i;
                while (end < line.length() && line.charAt(end) != ' ' && line.charAt(end) != '"') {
                    end++;
                }
                tokens.add(line.substring(i, end));
                i = end;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Question[] questions = new Question[n];
        for (int i = 0; i < n; i++) {
            // tokens: type, questionText, correctAnswer, studentAnswer, points
            List<String> t = tokenize(sc.nextLine().trim());
            int points = Integer.parseInt(t.get(4));
            switch (t.get(0)) {
                case "MCQ":   questions[i] = new McqQuestion(t.get(2), t.get(3), points); break;
                case "TF":    questions[i] = new TrueFalseQuestion(t.get(2), t.get(3), points); break;
                case "ESSAY": questions[i] = new EssayQuestion(t.get(2), t.get(3), points); break;
            }
        }

        double total = 0;
        for (Question q : questions) {
            double score = q.grade();
            System.out.printf("%s: %.2f%n", q.type, score);
            total += score;
        }
        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
