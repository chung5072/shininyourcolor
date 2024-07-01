package xyz.shininyourcolor.starwhisper.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import xyz.shininyourcolor.starwhisper.R
import xyz.shininyourcolor.starwhisper.showMessage
import xyz.shininyourcolor.starwhisper.ui.theme.StarWhisperTheme

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        StarryCampsiteBackground()
    }
}

@Composable
fun StarryCampsiteBackground() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.starrycampsite),
            contentDescription = stringResource(R.string.app_concept),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // 화면 비율에 맞게 이미지를 조정
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            // UI: 별이 반짝이는 하늘
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // height
                .clickable {
                    showMessage(
                        context = context,
                        message = "긍정적인 말을 공유하는 화면"
                    )
                })

            // 하단에 터치 영역
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // height
            ) {
                // UI: 텐트
                Box(modifier = Modifier
                    .weight(1f) // width
                    .fillMaxHeight()
                    .clickable {
                        showMessage(
                            context = context,
                            message = "계정 관리하는 화면"
                        )
                    })
                // UI: 모닥불
                Box(modifier = Modifier
                    .weight(1f) // width
                    .fillMaxHeight()
                    .clickable {
                        showMessage(
                            context = context,
                            message = "속에 쌓아둔 말을 털어놓는 화면"
                        )
                    })
                // UI: 망원경
                Box(modifier = Modifier
                    .weight(1f) // width
                    .fillMaxHeight()
                    .clickable {
                        showMessage(
                            context = context,
                            message = "다시 보고 싶은 말들을 모아놓은 화면"
                        )
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen_preview() {
    StarWhisperTheme {
        MainScreen()
    }
}