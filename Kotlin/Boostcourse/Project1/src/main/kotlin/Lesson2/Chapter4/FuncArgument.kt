package Lesson2.Chapter4

fun sum(a:Int,b:Int) = a+b
fun mul(a:Int,b:Int) = a*b

fun funcFunc(a:Int, b:Int) = sum(a,b)

fun main() {
    val result = sum(5,6)
    val result2 = mul(sum(10,5),10)
    val result3 = funcFunc(2,3)
    println("result: $result result2: $result2 result3: $result3") // 11 150 5
}