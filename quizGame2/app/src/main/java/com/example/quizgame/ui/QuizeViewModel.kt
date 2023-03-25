package com.example.quizgame.ui

import androidx.lifecycle.ViewModel
import com.example.quizgame.QuizQuestion
import com.example.quizgame.ListOfQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizUiState(
    val currentQuestion: QuizQuestion,
    val options: List<String>,
    val score: Int,
    val quizNumber: Int
)

class QuizViewModel : ViewModel() {

    private val quizData = ListOfQuestion()
    private var questions = quizData.questions.shuffled()
    private var quizNumber = 0
    private var score = 0

    private val _uiState = MutableStateFlow(QuizUiState(
        currentQuestion = questions[quizNumber],
        options = questions[quizNumber].options.shuffled(),
        score = score,
        quizNumber = quizNumber + 1
    ))

    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        updateUiState()
    }

    fun answerQuestion(answer: String) {
        if (answer == questions[quizNumber].correctAnswer) {
            score++
        }

        quizNumber++

        if (quizNumber < questions.size) {
            updateUiState()
        } else {
            endQuiz()
        }
    }

    fun resetQuiz() {
        questions = quizData.questions.shuffled()
        quizNumber = 0
        score = 0

        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = QuizUiState(
            currentQuestion = questions[quizNumber],
            options = questions[quizNumber].options.shuffled(),
            score = score,
            quizNumber = quizNumber + 1
        )
    }

    private fun endQuiz() {
        _uiState.value = QuizUiState(
            currentQuestion = QuizQuestion("", listOf(), correctAnswer = ""),
            options = listOf(),
            score = score,
            quizNumber = quizNumber
        )
    }

}
