package Lesson4.Chapter6

/*
takeIf() : 람다식이 true면 결과this를 반환, false면 null
takeUnless() : 람다식이 false면 결과를 반환
 */

fun main() {
    val input = "kotlin"
    val keyword = "in"

    //입력문자열에 키워드가 있으면 인덱스를 반환하는 함수
    val index = input.indexOf(keyword).takeIf { it>=0 } ?: error("keyword not found")
    val index2 = input.indexOf(keyword).takeUnless { it<0 } ?: error("keyword not found")

    println(index) //4
    println(index2) //4
}