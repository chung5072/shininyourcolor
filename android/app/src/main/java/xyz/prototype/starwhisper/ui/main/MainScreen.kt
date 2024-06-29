package xyz.prototype.starwhisper.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.prototype.starwhisper.R
import xyz.prototype.starwhisper.ui.theme.StarWhisperTheme

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        StarryCampsite()
    }
}

@Composable
fun StarryCampsite(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.starrycampsite),
            contentDescription = stringResource(R.string.app_concept),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // 화면 비율에 맞게 이미지를 조정
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen_preview() {
    StarWhisperTheme {
        MainScreen()
    }
}