package Lesson2.Chapter4

fun main() { // infixFun

    val num = 3
    //val str = num.strplus("kotlin")
    val str = num strplus "kotlin" //중위 표현법
    print(str) // kotlin version 3
}
// this 확장대상에 있던 객체를 나타낸다
infix fun Int.strplus(x:String) :String {
    return "$x version $this"
}