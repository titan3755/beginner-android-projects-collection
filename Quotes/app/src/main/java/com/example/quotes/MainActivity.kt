package com.example.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotes.ui.theme.QuotesTheme

val anton = FontFamily(
    Font(R.font.anton)
)

val bebas = FontFamily(
    Font(R.font.bebas_neue)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var quote by remember { mutableStateOf("Loading quote...") }
                    var author by remember { mutableStateOf("Author") }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Header(
                                content = "Quotes",
                                modifier = Modifier.padding(innerPadding)
                            )
                            Spacer(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .weight(1f) // Take up available space between header and body
                            )
                            Body(
                                quote = "The only way to do great work is to love what you do.",
                                author = "- Steve Jobs",
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(innerPadding)
                            )
                            Spacer(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .weight(1f) // Take up available space between content and button
                            )
                            ButtonBar(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 40.dp),
                                onClick = {
                                    // Update the quote and author when the button is clicked
                                    quote = "This is a new quote!"
                                    author = "- New Author"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontFamily = anton,
        modifier = modifier.fillMaxWidth().background(Color.Magenta).padding(10.dp)
    )
}

@Composable
fun Body(quote: String, author: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().padding(5.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = quote,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontFamily = bebas,
                lineHeight = 32.sp,
                modifier = modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(horizontal = 15.dp).padding(10.dp)
            )
            Text(
                text = author,
                color = Color.White,
                textAlign = TextAlign.End,
                fontSize = 24.sp,
                fontFamily = bebas,
                modifier = modifier.fillMaxWidth().align(Alignment.End).padding(horizontal = 32.dp).padding(10.dp)
            )
        }
    }
}

@Composable
fun ButtonBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(vertical = 40.dp),
        colors = ButtonColors(Color.Magenta, Color.White, Color.Magenta, Color.White)
    ) {
        Text(
            text = "Next Quote",
            color = Color.White,
            fontFamily = bebas,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuotationPreview() {
    QuotesTheme {
        // Simulate data for preview
        val simulatedQuote = "The only way to do great work is to love what you do."
        val simulatedAuthor = "- Steve Jobs"

        // Display the layout with a simulated quote and author
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Header(
                    content = "Quotes",
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) // To simulate vertical spacing
                Body(
                    quote = simulatedQuote,
                    author = simulatedAuthor,
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(16.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) // To simulate vertical spacing
            }
        }
    }
}