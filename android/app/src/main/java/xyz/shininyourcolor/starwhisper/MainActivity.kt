package xyz.shininyourcolor.starwhisper

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import xyz.shininyourcolor.starwhisper.ui.main.MainScreen
import xyz.shininyourcolor.starwhisper.ui.theme.StarWhisperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        enableEdgeToEdge()
        setContent {
            StarWhisperTheme {
                MainScreen()
            }
        }
    }
}

fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}