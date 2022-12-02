package Lesson2.Chapter4

import kotlin.coroutines.suspendCoroutine

fun main() {
    val result: Int
    //선언자료형 생략
    //val multi = {a:Int, b:Int -> a*b}
    //선언자료형 있는 람다
    val multi: (a: Int, b: Int) -> Int = { a, b -> a * b }
    //람다식이 할당된 변수는 함수처럼 사용가능
    result = multi(10,20)
    println(result)

    //표현식이 2줄 이상이면 마지막 줄이 반환됨

    // =========매개변수 개수에 따라 람다식 구성방법=========

    //매개변수가 없는 경우
    fun noParam(out:()->String) = println(out())
    noParam { "hello world" } //소괄호 생략됨

    //매개변수가 하나인 경우
    fun oneParam(out: (String)-> String){
        println(out("하세요"))
    }
    oneParam { "안녕$it" } // a->"hi" $a 를 it으로 대체

    //매개변수가 두 개 이상인 경우
    fun moreParam(out: (String,String)-> String){
        println(out("습니다", "안녕하세요"))
    }
    moreParam { a,b -> "반갑$a, $b" } // 2개이상은 매개변수명 생략불가

    //매개변수를 생략하고 싶다면
    moreParam { _,b -> "반갑, $b" } //_로 사용하지 않음을 표시

    //매개변수 안에 2개의 람다식을 가진 경우
    fun twoLambda(first:(String,String)->String, second:(String)->String){
        println(first("안녕하세요","반가워요"))
        println(second("잘있어요"))
    }
    twoLambda({a,b->"first $a $b"}){"second $it"} //마지막 람다 빼내기


    //람다식 안에 람다식이 있는 경우
    val nestedLambda:()->()->Unit = {{println("nested")}}
    nestedLambda()()



    // =========람다식 호출 방식=========

    //값에 의한 호출 / 람다식이름만 사용한 호출 / 다른 함수의 참조에 의한 호출

    //람다식이름을 사용한 호출
    fun callByName(b:()->Boolean):Boolean{ //매개변수에 람다반환값이 아닌 람다식 전체가 들어감
        println("매개변수에 람다식들어감")
        return b() //여기서 람다식 호출 = otherLambda 함수 실행
    }
    val otherLambda: () ->Boolean ={
        println("람다 함수")
        true
    }
    val lamb = callByName(otherLambda)
    println(lamb) //매개변수~가 먼저 출력되고 람다함수가 출력됨


    // 다른 함수의 참조에 의한 호출
    fun sum(x:Int,y:Int) = x+y
    fun funcParam(a:Int, b:Int, c: (Int,Int)->Int):Int{
        return c(a,b)
    }
    funcParam(3,2,::sum) //일반함수에서 람다식으로 넣으려면 ::함수이름

}
