package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdaycard.ui.theme.BirthdayCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayCardTheme {
                Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.fillMaxSize().padding(20.dp).then(Modifier.padding(innerPadding)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        BdayGreeting("Android", Modifier.padding(bottom = 8.dp))
                        BdayGreeter("Jetpack Compose", Modifier.padding(top = 8.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(top = 16.dp),
                            colors = ButtonColors(
                                containerColor = Color.Green,
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

@Composable
fun BdayGreeting(name: String, modifier: Modifier = Modifier) {
    Text (
        text = "Happy Birthday, $name!",
        color = Color.White,
        modifier = modifier
            .fillMaxWidth(),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Visible,
        lineHeight = TextUnit(1.2f, TextUnitType.Em)
    )
}

@Composable
fun BdayGreeter(name: String, modifier: Modifier = Modifier) {
    Text (
        text = "From $name",
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.End,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BirthdayCardTheme {
        Scaffold (modifier = Modifier.padding(16.dp)) { innerPadding ->
            Column (
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BdayGreeting("Android", Modifier.padding(bottom = 8.dp))
                BdayGreeter("Jetpack Compose", Modifier.padding(top = 8.dp))
            }
        }
    }
}