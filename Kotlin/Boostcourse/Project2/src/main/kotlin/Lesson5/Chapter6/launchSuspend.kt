package Lesson5.Chapter6

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun doWork1():String{
    delay(1000)
    return "w1"
}

suspend fun doWork2():String{
    delay(3000)
    return "w2"
}

private fun worksInSerial(): Job{
    val job = GlobalScope.launch {
        val one = doWork1() //1초지연
        val two = doWork2() //3초지연
        println("one: $one") //시작 4초후 출력
        println("two: $two")
    }
    return job //job 반환해서 메인이 바로 종료되는것을 멈추기위해
}
fun main()  = runBlocking{
    val time = measureTimeMillis {
        val job = worksInSerial()
        //readLine
        job.join()
    }
    println("${time}ms")
}