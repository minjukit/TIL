package Lesson1.Chapter2

fun main(){
    //정수 실수 자료형 min~max
    println("Byte: ${Byte.MIN_VALUE}~${Byte.MAX_VALUE}")
    println("Int: ${Int.MIN_VALUE}~${Int.MAX_VALUE}")
    println("Short: ${Short.MIN_VALUE}~${Short.MAX_VALUE}")
    println("Long: ${Long.MIN_VALUE}~${Long.MAX_VALUE}")
    println("Float: ${Float.MIN_VALUE}~${Float.MAX_VALUE}")
    println("Double: ${Double.MIN_VALUE}~${Double.MAX_VALUE}")

    /* 2의 보수
    * 음수의 표현 = 2의 보수
    * 절대값의 이진수 -> 값 뒤집기 -> 1 더하기
     */

    val num = 0x3F
    println("${num::class.simpleName}") //Int
    println("${num.javaClass}") //int
}