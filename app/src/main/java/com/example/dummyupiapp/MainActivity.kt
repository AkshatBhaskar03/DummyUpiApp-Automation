package com.example.dummyupiapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dummyupiapp.ui.theme.DummyUpiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        if (intent != null && intent.data != null) {
            val uri: Uri = intent.data!!
            Log.d("URI_DATA", uri.toString())
            // Handle the deep link URI
            // ...
        }
        setContent {
            DummyUpiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    )
                    {
                        var buttonList = listOf<String>("Sucess","Failure","Pending");
                        buttonList.forEachIndexed{
                            index: Int, s: String ->  SimpleButton(s, onClick = { handleButtonClick(s) })
                        }
                    }
                }
            }
        }
    }

    private fun handleButtonClick(buttonType: String) {
        Toast.makeText(this, "Button $buttonType clicked", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SimpleButton(name: String,  onClick : () -> Unit) {
        // Button composable with onClick listener
        Button(onClick = onClick) {
            Text(name)
        }
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    SimpleButton("Akshat" , onClick = { Log.d("Logger","1") } )
}