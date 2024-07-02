package xyz.shininyourcolor.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class AccountApplication

fun main(args: Array<String>) {
	runApplication<AccountApplication>(*args)
}
