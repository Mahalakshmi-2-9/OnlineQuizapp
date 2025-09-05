package com.quizapp.service;

import com.quizapp.model.Question;
import java.io.*;
import java.util.*;

public class QuizService {
    private List<Question> questions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Load questions from resources or fallback file
    public void loadQuestions() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questions.txt");

            if (inputStream == null) {
                File file = new File("src/resources/questions.txt");
                if (!file.exists()) {
                    System.out.println("❌ Questions file not found: " + file.getAbsolutePath());
                    return;
                }
                inputStream = new FileInputStream(file);
            }

            Scanner sc = new Scanner(inputStream);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                // Format: question;opt1;opt2;opt3;opt4;correctIndex
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String questionText = parts[0];
                    String[] options = Arrays.copyOfRange(parts, 1, 5);
                    int correctAnswer = Integer.parseInt(parts[5].trim());
                    questions.add(new Question(questionText, options, correctAnswer));
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("❌ Error loading questions: " + e.getMessage());
        }
    }

    // Start the quiz
    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions available!");
            return;
        }

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Hi!\nWelcome to the Quiz.\nAre you ready for the test?");
        System.out.println("1: yes");
        System.out.println("2: no");
        System.out.print("Enter the number: ");

        String readyInput = scanner.nextLine().trim();
        if (!readyInput.equals("1")) {
            System.out.println("Okay, maybe next time!");
            return;
        }

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total number of quiz questions: " + questions.size());

        int score = 0;
        int qNumber = 1;

        for (Question q : questions) {
            System.out.println("\n" + qNumber + ") " + q.getQuestionText());
            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int answer = 0;
            while (true) {
                System.out.print("Your answer (1-4): ");
                try {
                    answer = Integer.parseInt(scanner.nextLine());
                    if (answer >= 1 && answer <= 4) break;
                    else System.out.println("Please enter a number between 1 and 4.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Enter a number between 1 and 4.");
                }
            }

            if (answer - 1 == q.getCorrectAnswer()) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + options[q.getCorrectAnswer()]);
            }

            qNumber++;
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\nQuiz Completed! Your score: " + score + "/" + questions.size());
        System.out.println("Come again and improve your skills!");
        System.out.println("Thank you for participating!");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Show main menu
    public void showMenu() {
        System.out.println("Welcome to the Quiz World!");
        while (true) {
            System.out.println("\n========================================== QUIZ MENU =======================================================================================================================================");
            System.out.println("1. Start Quiz");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    startQuiz();
                    break;
                case "2":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        }
    }
}
