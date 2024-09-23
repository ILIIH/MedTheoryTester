package com.example.medtheorytester.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medtheorytester.R
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
    val riddle = eventsViewModel.currentRiddle

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = riddle.value?.question ?: String(),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 15.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 30.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            riddle.value?.answers?.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    OptionItem(
                        isTrue = option.isTrue,
                        optionText = option.answer,
                        isSelected = selectedOption == index,
                        onClick = {
                            if (selectedOption == null) selectedOption = index
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Image(
            painter = painterResource(id = R.drawable.arrow_righ),
            contentDescription = "Quiz Image",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(100.dp)
                .clickable {
                    selectedOption = null
                    eventsViewModel.next()
                }
        )
    }
}

@Composable
fun OptionItem(
    isTrue:Boolean,
    optionText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected&& isTrue) Color.Green else if(isSelected && !isTrue) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = Color.Gray,
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
