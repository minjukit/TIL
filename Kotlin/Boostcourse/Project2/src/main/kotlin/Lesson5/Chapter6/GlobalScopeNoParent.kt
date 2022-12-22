package Lesson5.Chapter6

import kotlinx.coroutines.*

fun main() = runBlocking{
    println("runBlocking: $coroutineContext")
    val request = launch {
        println("request: $coroutineContext")
        GlobalScope.launch {// 프로그램 전역으로 독립적인 수행
            println("job1 before: $coroutineContext")
            delay(1000)
            println("job1 after: $coroutineContext")
        }
        launch {// 부모의 문맥을 상속 (상위 launch의 자식)
        //CoroutineScope(Dispatchers.Default).launch{ // 새로운 스코프가 구성되 request와 무관
            delay(100)
            println("job2 before: $coroutineContext")
            delay(1000)
            println("job2 after: $coroutineContext") //부모request가 취소되면 출력안됨
        }
    }
    delay(500)
    request.cancel() //부모 코루틴 취소 (job2 after는 출력안됨)
    delay(1000)
    
}