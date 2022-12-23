package Lesson5.Chapter6

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

suspend fun main() {
    val result = withTimeoutOrNull(1300L){
        repeat(1000){//1000번 반복하는데
            println("I'm sleeping $it") // 0, 1, 2 출력 1500까지는 가지 못함
            delay(500L)
        }
        "Done" //코루틴 블럭이 완료되면 이 값이 result에 반환됨
    }
    println("Result is $result") //null
}
