package xyz.shininyourcolor.account.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.io.FileInputStream
import java.io.FileNotFoundException

@Configuration
class FCMConfig(
    val env: Environment
) {
    /**
     * Firebase로 메세지 전송
     *
     * @exception FileNotFoundException 
     * 원하는 파일이 없는 경우
     */
    @PostConstruct
    fun run() {
        try {
            FirebaseApp.getInstance()
        } catch (e: Exception) {
            try {
                val serviceAccount =
                    FileInputStream(env.getProperty("fcm_setting_file_path")!!)

                val options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()

                FirebaseApp.initializeApp(options)
            } catch (fileException: FileNotFoundException) {
                println("에러 - 해당 위치에 파일이 없음")
            }
        }
    }
}