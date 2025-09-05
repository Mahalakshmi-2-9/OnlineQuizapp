package com.quizapp.main;

import com.quizapp.service.QuizService;

public class Main {
    public static void main(String[] args) {
        QuizService quizService = new QuizService();
        quizService.loadQuestions();  // Load questions
        quizService.showMenu();       // Show menu to start quiz
    }
}
