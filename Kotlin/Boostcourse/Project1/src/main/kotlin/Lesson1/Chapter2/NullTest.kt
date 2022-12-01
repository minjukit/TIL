package Lesson1.Chapter2

fun main(){
    var str1: String?
    str1 = null
    var str2 = "maerong"

    // safe-call(?.)
    println(str1?.length) // null 이면 null 출력

    //println(str1!!.length) //NPE이지만 컴파일에서 알려줌

    //elvis operator(?:)
    val len = str1?.length ?: -1
    println("[LEN] str1: $len , str2: ${if (str2 != null) str2.length else -1}")
}