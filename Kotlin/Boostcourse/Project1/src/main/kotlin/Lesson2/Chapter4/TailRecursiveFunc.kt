package Lesson2.Chapter4

fun main(){
    val number = 5
    println("Factorial: $number -> ${fac(number)}")
}
//  꼬리 재귀 함수(tail recursive function)를 통해 스택 오버플로 현상을 해결
tailrec fun fac(n: Int, run :Int= 1):Long{
    return if(n==1) run.toLong() else fac(n-1, run*n)
}