package com.example.medtheorytester.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medtheorytester.viewModel.QuizViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizScreen(
    question: String,
    options: List<String>,
    navController: NavHostController?
) {
    val eventsViewModel = koinViewModel<QuizViewModel>()
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val riddle by rememberSaveable { eventsViewModel.currentRiddle }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = riddle?.question?:String(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start)
                .padding(bottom = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        riddle?.answers?.forEachIndexed { index, option ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OptionItem(
                    optionText = option.answer,
                    isSelected = selectedOption == index,
                    onClick = {
                        selectedOption = index
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
fun OptionItem(
    optionText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = optionText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    val question = "What is the capital of France?"
    val options = listOf("Paris", "London", "Berlin", "Madrid")

    QuizScreen(
        question = question,
        options = options,
        null
    )
}
