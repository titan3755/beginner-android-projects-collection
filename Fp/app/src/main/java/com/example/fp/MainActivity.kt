package com.example.fp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fp.ui.theme.FpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        AltGreeting(
                            name = "john",
                            modifier = Modifier.padding(innerPadding)
                        )
                        SuperAltGreeting(
                            name = "diddy",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Red,
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize() // Make sure Box fills the entire Surface
        ) {
            Text(
                text = "Hello $name!",
                color = Color.White,
                modifier = Modifier
                    .padding(20.dp)
                    .then(modifier)
                    .align(Alignment.Center) // Center the text inside the Box
            )
        }
    }
}

@Composable
fun AltGreeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Blue,
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize() // Make sure Box fills the entire Surface
        ) {
            Text(
                text = "Ayyyy waddap, $name!",
                color = Color.White,
                modifier = Modifier
                    .padding(20.dp)
                    .then(modifier)
                    .align(Alignment.Center) // Center the text inside the Box
            )
        }
    }
}

@Composable
fun SuperAltGreeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Green,
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize() // Make sure Box fills the entire Surface
        ) {
            Text(
                text = "Greetings, Mr. $name.",
                color = Color.White,
                modifier = Modifier
                    .padding(20.dp)
                    .then(modifier)
                    .align(Alignment.Center) // Center the text inside the Box
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FpTheme {
        Greeting("Android")
    }
}