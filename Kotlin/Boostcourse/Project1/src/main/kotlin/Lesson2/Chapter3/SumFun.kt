package Lesson2.Chapter3



fun main() {

    fun sum(a: Int = 10, b: Int): Int = a + b
    fun outfun(name: String) = println("이름: $name")

    val result = sum(b = 5)
    println(result)
    outfun("minju")
}