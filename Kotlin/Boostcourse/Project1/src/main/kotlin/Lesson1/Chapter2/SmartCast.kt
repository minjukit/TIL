package Lesson1.Chapter2

fun main(){
    val a = 3
    val b= a.toDouble() //자료형 변환 메서드
    val result = 1L+3 //표현식에서 자료형 자동변환 (Long + Int)

    //스마트 캐스트
    // Any -> Number -> 수 자료형
    var test: Number = 12.2 //Float로 스마트캐스트 (String으로는 불가능)
    test = 12
    println("${test::class.simpleName}")// Int
    test = 120L
    println("${test::class.simpleName}")// Long
    test += 12.0f
    println("${test::class.simpleName}")//Float

    val num = 256
    if(num !is Int) println("not Int") else println(num)

}