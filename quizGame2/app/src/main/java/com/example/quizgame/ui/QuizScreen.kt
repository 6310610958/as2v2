package com.example.quizgame.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizgame.QuizQuestion


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuizScreen(quizViewModel: QuizViewModel , onPlayAgain: () -> Unit, onExit: () -> Unit) {

    val uiState by quizViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz App") }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),

                ) {
                when (uiState.quizNumber) {
                    10 -> {
                        SummaryScreen(
                            score = uiState.score,
                            onPlayAgain = onPlayAgain,
                            onExit = onExit
                        )
                    }
                    else -> {
                        QuestionScreen(
                            question = uiState.currentQuestion,
                            options = uiState.options,
                            score = uiState.score,
                            quizNum = uiState.quizNumber,
                            answer = uiState.options,
                            SelectedAnswer = quizViewModel::answerQuestion
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun QuestionScreen(question: QuizQuestion, options: List<String>, score: Int, quizNum: Int, answer: List<String>, SelectedAnswer: (String) -> Unit)  {

    Column(
        modifier = Modifier.padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "$quizNum/10")
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                text = "score: $score")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = question.question)

        Spacer(modifier = Modifier.height(16.dp))

        options.forEach { choice ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                onClick = { SelectedAnswer(choice) }
            ) {
                Text(text = choice)
            }
        }
    }
}

@Composable
fun SummaryScreen(score: Int, onPlayAgain: () -> Unit, onExit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your score is $score out of 10",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onPlayAgain,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Play Again")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onExit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Exit")
        }
    }
}