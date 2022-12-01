package Lesson1.Chapter2

/*문자열
* String은 동적공간 HEAP의 String Pool에 저장됨
* 참조 주소는 가변형으로 바뀔 수 있지만
* 생성된 메모리공간은 변경되지 않음
* == 로 값 비교, === 로 값&참조 비교
* 자바에서는 ==로 값과 참조주소 비교
 */

fun main(){
    //var, val 같은 결과
    var str1: String = "seoul"
    var str2: String = "newyork"
    var str3 : String = "seoul"
    println("${str1 == str2} and ${str1 === str2}")
    println("${str1 == str3} and ${str1 === str3}")
}