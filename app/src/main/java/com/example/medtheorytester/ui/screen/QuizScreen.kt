package com.example.medtheorytester.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medtheorytester.R
import com.example.medtheorytester.ui.theme.primaryColor
import com.example.medtheorytester.ui.theme.primaryGreenColor
import com.example.medtheorytester.viewModel.QuizViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizScreen(
    navController: NavHostController
) {
    val eventsViewModel = koinViewModel<QuizViewModel>()
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isOptionWrong by remember { mutableStateOf<Boolean>(false) }

    val riddle = eventsViewModel.currentRiddle
    val questionNumber = eventsViewModel.index

    if(eventsViewModel.isLoading.value){
        SplashScreen(null)
    }
    else{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 90.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Питання № : ${questionNumber + 1}",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 13.sp),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 30.dp)
                )
                Text(
                    text = riddle.value?.question ?: String(),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 13.sp),
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
                            isSelected = selectedOption ,
                            index = index,
                            onClick = {
                                if (selectedOption == null) selectedOption = index
                                if (!option.isTrue) isOptionWrong = true
                            },
                            endIndex = riddle.value?.answers?.size?:0
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            selectedOption = null
                            eventsViewModel.next()
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val topLineStart = Offset(-60f, 0f)
                        val topLineEnd = Offset(size.width + 60f, strokeWidth / 2)

                        drawLine(
                            color = Color.LightGray,
                            start = topLineStart,
                            end = topLineEnd,
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Square,
                        )
                    },
                contentAlignment = Alignment.Center,
                ){
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    text = "Continue",
                    color = primaryColor
                    )
            }
        }
    }

}

@Composable
fun OptionItem(
    isTrue:Boolean,
    optionText: String,
    isSelected: Int?,
    index: Int,
    onClick: () -> Unit,
    endIndex: Int
) {
    val modifierShape = when (index) {
        0 -> RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
        endIndex -1 -> RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
        else -> RoundedCornerShape(0.dp)
    }
    val backgroundColor =  if (isSelected != null && isTrue) primaryGreenColor else if(isSelected == index && !isTrue) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface;
    val textColor = if((isSelected == index )|| (isTrue && isSelected!= null)) Color.White else Color.Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = modifierShape
            )
            .clip(modifierShape)
            .background(backgroundColor)
    ) {
        Text(
            text = optionText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
            color = textColor
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    val navController = rememberNavController();
    QuizScreen(
        navController =navController
    )
}
