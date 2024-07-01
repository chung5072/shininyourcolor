package xyz.shininyourcolor.starwhisper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import xyz.shininyourcolor.starwhisper.ui.main.MainScreen
import xyz.shininyourcolor.starwhisper.ui.theme.StarWhisperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        enableEdgeToEdge()

        setContent {
            StarWhisperTheme {
                // showDialog 상태 관리
                var showDialog by remember { mutableStateOf(false) }

                // showDialog 상태를 BackOnPressed에 전달
                BackOnPressed(
                    showDialog = showDialog,
                    setShowDialog = { show ->
                        showDialog = show
                    }
                )
                // 메인 화면
                MainScreen()
            }
        }
    }
}

/**
 * 사용자에게 피드백 제공하는 Toast 메세지 함수
 *
 * @param context 애플리케이션의 현재 상태, 시스템이 관리하고 있는 액티비티, 어플리케이션의 정보
 * @param message 메세지로 보여줄 내용
 */
fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * 뒤로 가기를 눌렀을 때, 보여줄 대화 상자
 *
 * @param showDialog 대화 상자 표시 여부
 * @param setShowDialog 대화 상자의 표시 여부를 제어하는 콜백 함수
 * 콜백 함수: 다른 함수에 인자로 전달되는 함수
 */
@Composable
fun BackOnPressed(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }

    BackHandler(enabled = backPressedState) {
        setShowDialog(true)
    }

    if(showDialog) {
        ShowAlertDialog(
            context = context,
            title = "잠들기",
            description = "앱을 종료하시겠습니까?",
            posText = "별을 더 구경하기",
            negText = "잠들기[종료하기]",
            onDismiss = { setShowDialog(false) }
        )
    }
}

/**
 * 앱 종료를 보여줄 대화 상자
 *
 * @param context 시스템이 관리하고 있는 액티비티, 어플리케이션의 정보
 * @param title 대화 상자 제목
 * @param description 대화 상자 설명
 * @param posText 우측 버튼 내용
 * @param negText 좌측 버튼 내용
 * @param onDismiss 대화 상자의 표시 여부를 제어하는 콜백 함수
 */
@Composable
fun ShowAlertDialog(
    context: Context,
    title: String,
    description: String,
    posText: String,
    negText: String,
    onDismiss: () -> Unit
) {
    AlertDialog.Builder(context)
        .setTitle("$title")
        .setMessage("$description")
        .setPositiveButton("${posText}") { dialog, which ->
            // 긍정 버튼 클릭 동작 처리
            showMessage(
                context = context,
                message = "별들이 반짝이고 있어요!"
            )
        }
        .setNegativeButton("${negText}") { dialog, which ->
            // 부정 버튼 클릭 동작 처리
            (context as Activity).finish()
        }
        .setOnDismissListener {
            // 대화 상자가 닫힐 때 호출되는 콜백
            onDismiss()
        }
        .show()
}