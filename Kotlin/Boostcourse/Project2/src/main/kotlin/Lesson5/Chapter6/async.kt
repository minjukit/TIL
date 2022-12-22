package Lesson5.Chapter6


import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun dWork1():String{
    delay(1000)
    return "w1"
}

suspend fun dWork2():String{
    delay(3000)
    return "w2"
}

private fun worksInParallel():Job{
    val one = GlobalScope.async {
        dWork1()
    }
    val two = GlobalScope.async {
        dWork2()
    }
    val job = GlobalScope.launch {
        val combined = one.await() + "_"+two.await()
        println("$combined")
    }
    return job
}

fun main()  = runBlocking{
    val time = measureTimeMillis {
       val job = worksInParallel()
        job.join()
    }

    println("${time}ms") //4초보다 빨리 실행됨(비동기)
}