package xyz.shininyourcolor.starwhisper

import android.os.Bundle
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