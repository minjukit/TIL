package Lesson2.Chapter3

// Lambda Expression
// 익명함수의 하나의 형태
// 매개변수 -> 반환

fun highFunc(a:Int, b:Int, sum:(Int,Int)->Int):Int{
    return sum(a,b)
}

//람다식이 마지막 매개변수로 존재하면 소괄호에서 빼내기
fun main() {
    val result = highFunc(4,6) { x, y -> x + y }
    print(result)
}

/* 함수형 프로그래밍
* 프로그램을 모듈화해 디버깅/테스트가 쉬움
* 간략한 표현식으로 생산성이 높음
* 순수함수를 조합해 람다식으로 고차함수를 구성함
 */
