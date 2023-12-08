package com.example.dummyupiapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dummyupiapp.ui.theme.DummyUpiAppTheme

class MainActivity : ComponentActivity() {

    private val FAILED_CASE = 123;
    private var upiUrlData = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        if (intent != null && intent.data != null) {
            val uri: Uri = intent.data!!
            upiUrlData = uri.toString()
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
                        var buttonList = listOf<String>("Success", "User Cancel", "Failure");
                        Text("Intent URL : ")
                        TextBoxIntentData(text = upiUrlData)
                        buttonList.forEachIndexed { index: Int, s: String ->
                            SimpleButton(s, onClick = { handleButtonClick(s) })
                        }
                    }
                }
            }
        }
    }

    private fun handleButtonClick(buttonType: String) {
        when (buttonType) {
            "Success" -> {
                val responseMsg = "Payment Successful"
                val resultIntent = Intent()
                resultIntent.putExtra("response", responseMsg)
                setResult(RESULT_OK, resultIntent)
            }

            "User Cancel" -> {
                setResult(RESULT_CANCELED)
            }

            "Failure" -> {
                setResult(FAILED_CASE)
            }
        }
        finish()
    }
}

@Composable
fun TextBoxIntentData(text: String) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(600.dp)
            .padding(12.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            fontSize = 14.sp,
        )
    }
}

@Composable
fun SimpleButton(name: String, onClick: () -> Unit) {
    // Button composable with onClick listener
    Button(onClick = onClick) {
        Text(name)
    }
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    SimpleButton("Akshat", onClick = { Log.d("Logger", "1") })
}

@Preview(showBackground = true)
@Composable
fun TextBoxIntentDataPreview() {
    TextBoxIntentData("Akshat")
}