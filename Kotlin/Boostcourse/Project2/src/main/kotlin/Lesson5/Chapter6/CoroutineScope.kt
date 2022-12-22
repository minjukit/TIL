package Lesson5.Chapter6

import kotlinx.coroutines.*

fun main() = runBlocking(Dispatchers.Default){//main함수 자체가 런블러킹 코루틴스코프가 됨

    launch(Dispatchers.IO) {
        delay(400L)
        println("from runBlocking")
    }
    coroutineScope {
        launch {
            delay(400L)
            println("from nested launch")
        }
        delay(400L)
        println("from coroutineScope")
    }
    println("end of runBlocking") //coroutineScope 내 작업이 완료되면 출력 ("from runBlocking")와는 무관
}