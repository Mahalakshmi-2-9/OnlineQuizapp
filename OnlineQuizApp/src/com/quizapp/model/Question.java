package com.quizapp.model;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer; // index 0-3

    // ✅ Constructor matching QuizService usage
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
