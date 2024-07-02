package xyz.shininyourcolor.starwhisper.ui.account

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import xyz.shininyourcolor.starwhisper.showMessage

@Composable
fun AccountDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            AccountDialogContent(
                title = "텐트 정리",
                description = "앱 종료하기",
                positiveText = "종료",
                negativeText = "비활성화",
                onClickOk = {closeApp(context = context)},
                onClickNo = {unactivateApp(context = context)}
            )
        }
    }
}

@Composable
fun AccountDialogContent(
    title: String,
    description: String = "",
    positiveText: String,
    negativeText: String,
    onClickOk: () -> Unit,
    onClickNo: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Text(
            title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 16.sp,
            lineHeight = 17.sp
        )
        Text(
            description,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // height
                .padding(vertical = 8.dp),
            fontSize = 10.sp,
            lineHeight = 10.sp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // height
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { onClickOk() },
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(positiveText, fontSize = 16.sp)
            }
            Button(
                onClick = { onClickNo() },
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(negativeText, fontSize = 16.sp)
            }
        }
    }
}

fun closeApp(context: Context) {
    (context as Activity).finish()
}

fun unactivateApp(context: Context) {
    showMessage(
        context = context,
        message = "앱에 비활성화 저장 및 공유 중지할 것인지 물어봄"
    )
    (context as Activity).finish()
}
