package Lesson5.Chapter6


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        val job = GlobalScope.launch { //background에서 새 코루틴 실행
            delay(1000L) // 1초의 넌블로킹 지연
            println("hi2") //지연 후 출력

        }
        println("hi") // main스레드가 코루틴이 지연되는 동안 계속 실행
        println("job: ${job.isActive}, ${job.isCompleted}") //true, false
        //Thread.sleep(2000L)
        job.join()
        println("job: ${job.isActive}, ${job.isCompleted}") //false, true
    }
}
