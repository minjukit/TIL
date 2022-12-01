package Lesson1.Chapter2

fun main(){
    // 기본형 (Stack에 존재)
    val a = 128
    val b = a

    println(a === b) //true

    // 참조형
    // Nullable 은 객체 참조형이라 (Heap에 독립 공간 존재)
    // 코틀린엔서 참조형으로 선언한 변수의 값이 -128~127 사이에 있으면 캐시에 값을 저장함
    val c:Int? = a
    val d:Int? = a
    val e:Int? = c

    println(c == d) //true
    println(c === d) //false
    println(c === e) // true
}