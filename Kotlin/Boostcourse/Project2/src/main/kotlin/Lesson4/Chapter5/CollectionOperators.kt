package Lesson4.Chapter5

fun main() {
    val list1 = listOf("one","hi","hello")
    val list2 = listOf(1,5,3)
    val map1 = mapOf("jj" to "j", "hi2" to "hi", "hello" to "jkj")

    println(list1+"four")
    println(list2 - 5) //요소5 뺌
    println(list2 + listOf(7,8))
    println(map1 - listOf("hi","hello")) //hello 키값만 삭제
}