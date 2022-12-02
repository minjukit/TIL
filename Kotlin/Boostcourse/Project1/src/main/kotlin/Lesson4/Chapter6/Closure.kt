package Lesson4.Chapter6

/* 클로저
 * 람다식 내부에서 외부범위에 선언된 변수에 접근할 수 있음
 * 람다식 안 외부변수는 값을 유지하기위해 람다가 포획한 변수
 * 함수의 매개변수도 접근 가능
 */
fun main() {
    val closure = Closure()
    var result = 0 //외부 변수
    closure.addNum(2,3){x,y-> result=x+y} //클로저
    println(result) //5 출력
}

class Closure {
    fun addNum(a:Int,b:Int,add:(Int,Int)->Unit){
        add(a,b)
    }
}