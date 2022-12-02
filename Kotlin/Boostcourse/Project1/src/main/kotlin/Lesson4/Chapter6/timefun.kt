package Lesson4.Chapter6

import kotlin.system.measureTimeMillis

/*
kotlin.system 패키지에 있는 2개의 측정 함수
- measureTimeMillis()
- measureNanoTime()
 */

import kotlin.random.Random

fun main() {

    val executionTime = measureTimeMillis {
        for (i in 1..10)
            print("${Random.nextInt(100)} ")
    }
    println()
    println("${executionTime}ms")
}