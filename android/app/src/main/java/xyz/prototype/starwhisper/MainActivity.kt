package xyz.prototype.starwhisper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import xyz.prototype.starwhisper.ui.main.MainScreen
import xyz.prototype.starwhisper.ui.theme.StarWhisperTheme

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