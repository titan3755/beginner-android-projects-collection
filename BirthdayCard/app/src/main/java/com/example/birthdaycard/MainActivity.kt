package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdaycard.ui.theme.BirthdayCardTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

val birthdayFont = FontFamily(
    Font(R.font.birthday)
)

val authorFont = FontFamily(
    Font(R.font.siv)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val image = painterResource(R.drawable.birthdaybg)
            BirthdayCardTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent, // No background color, image will fill
                    contentColor = Color.Black,
                    contentWindowInsets = WindowInsets(0.dp)
                ) { innerPadding ->
                    var isConfettiVisible by remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // Use innerPadding to manage system padding
                    ) {
                        // Background image, fill the whole screen without any system padding
                        Image(
                            painter = image,
                            contentDescription = "Birthday Card Background",
                            modifier = Modifier
                                .fillMaxSize()
                                .windowInsetsPadding(WindowInsets(0.dp)), // Account for system bars and insets
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillBounds
                        )
                        // Foreground content
                        if (isConfettiVisible) {
                            ConfettiView()
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BdayGreeting("Android", Modifier.padding(bottom = 8.dp))
                            BdayGreeter("Jetpack Compose", Modifier.padding(top = 8.dp))
                            Button(
                                onClick = { isConfettiVisible = !isConfettiVisible },
                                modifier = Modifier.padding(top = 16.dp),
                                colors = ButtonColors(
                                    containerColor = Color.Magenta,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Gray,
                                    disabledContainerColor = Color.Gray
                                )
                            ) {
                                Text("Celebrate!")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BdayGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Happy Birthday, $name!",
        color = Color.White,
        modifier = modifier
            .fillMaxWidth(),
        style = LocalTextStyle.current.merge(
            TextStyle (
                color = Color(0xFFF67C37),
                fontSize = 40.sp,
                drawStyle = Stroke(width = 6f, join = StrokeJoin.Round)
            )
        ),
        fontFamily = birthdayFont,
        fontSize = 55.sp,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Visible,
        lineHeight = TextUnit(1.2f, TextUnitType.Em)
    )
}

@Composable
fun BdayGreeter(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "- From $name",
        color = Color(0xFFFFD700),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        style = LocalTextStyle.current.merge(
            TextStyle (
                color = Color.White,
                fontSize = 20.sp,
                drawStyle = Stroke(width = 2f, join = StrokeJoin.Round)
            )
        ),
        fontFamily = authorFont,
        fontSize = 20.sp,
        textAlign = TextAlign.End,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BirthdayCardTheme {
        Scaffold(modifier = Modifier.padding(16.dp)) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Background image
                Image(
                    painter = painterResource(R.drawable.birthdaybg),
                    contentDescription = "Birthday Card Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets(0.dp)), // Ensure the system bars are properly handled
                    contentScale = ContentScale.FillBounds // Stretch the image
                )
                // Foreground content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    BdayGreeting("Android", Modifier.padding(bottom = 8.dp))
                    BdayGreeter("Jetpack Compose", Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}

data class Confetti(var x: Float, var y: Float, val color: Color, var velocityY: Float = 0f, var velocityX: Float = 0f)

@Composable
fun ConfettiView() {
    var confettiList by remember { mutableStateOf(List(50) { createConfetti() }) }
    val isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            confettiList = confettiList.map { confetti ->
                confetti.y += confetti.velocityY
                confetti.x += confetti.velocityX
                confetti.velocityY += 0.5f // Gravity
                if (confetti.y > 2000f) { // Reset confetti when it goes off screen
                    createConfetti()
                } else {
                    confetti
                }
            }
            delay(16) // ~60 FPS
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        confettiList.forEach { confetti ->
            drawCircle(
                color = confetti.color,
                radius = 8f,
                center = Offset(confetti.x, confetti.y),
                style = Fill
            )
        }
    }
}

fun createConfetti(): Confetti {
    return Confetti(
        x = Random.nextFloat() * 1000f,
        y = -50f,
        color = Color(Random.nextInt()),
        velocityY = Random.nextFloat() * 10 - 5,
        velocityX = Random.nextFloat() * 20 - 10
    )
}
