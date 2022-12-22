package Lesson5.Chapter6

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch { //background에서 새 코루틴 실행
        delay(1000L) // 1초의 넌블로킹 지연
        println("hi2") //지연 후 출력
        doSomeThing()
     }
    println("hi") // main스레드가 코루틴이 지연되는 동안 계속 실행
    // 아래 문장을 쓰지 않으면 바로 종료되어 hi2 출력 안됨
    Thread.sleep(2000L) //main스레드가 JVM에서 바로 종료되지않게 2초기다림

}

suspend fun doSomeThing(){
    println("Do Something")
}