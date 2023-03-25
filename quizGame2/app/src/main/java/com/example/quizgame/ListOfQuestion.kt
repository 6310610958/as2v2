package com.example.quizgame

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

class ListOfQuestion {
    val questions = listOf(
        QuizQuestion(
            "What is the capital of France?",
            listOf("London", "Paris", "Madrid"),
            "Paris"
        ),
        QuizQuestion(
            "What is the largest country in the world by area?",
            listOf("China", "Russia", "USA"),
            "Russia"
        ),
        QuizQuestion(
            "What is the currency of Japan?",
            listOf("Yen", "Dollar", "Euro"),
            "Yen"
        )
    )
}