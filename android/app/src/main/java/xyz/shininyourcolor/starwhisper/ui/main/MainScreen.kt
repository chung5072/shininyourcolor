package xyz.shininyourcolor.starwhisper.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import xyz.shininyourcolor.starwhisper.R
import xyz.shininyourcolor.starwhisper.ui.theme.StarWhisperTheme

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