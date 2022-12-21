package Lesson4.Chapter5
import java.util.*

fun main() {

    val squares = generateSequence(1) {it + 1}.map {it * it}
    println(squares.take(10).toList()) // [1,4,9, ... , 100]

    val oddSquares = squares.filter{it%2 != 0}
    println(oddSquares.take(5).toList())// [1,9,25,49,81]

    val list1 = listOf(1,2,3,4,5)
    val listSeq = list1.asSequence()
        .map{println("map($it)"); it * it}
        .filter { println("filter($it)"); it % 2 ==0}
        .toList()
    println(listSeq) // [4, 16]
}