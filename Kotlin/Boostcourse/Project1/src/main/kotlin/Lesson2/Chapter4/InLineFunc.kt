package Lesson2.Chapter4

// 익명함수 anonymous fun은 return,break,continue가 사용가능
// 하지만 람다식에서는 사용하기 어렵고 라벨표기법과 함께 사용해야 함

/* 인라인 함수
* 분기없이 처리한다
* 함수가 호출되는 곳에 내용을 모두 그대로 복사한다 (점프안함)
* 코드가 복사되므로 많은 함수에 사용하면 코드가 늘어남(단점)
*
* noinline : 람다식 인라인 막음
 */
inline fun shortFunc(a:String, noinline out:(String) -> Unit){
    println(a)
    out(a) //noinline = bytecode에서 확인가능
}

inline fun shortFun(a:Int,out:(Int) -> Unit){
    //비지역반환 금지시키려면 crossinline
    //inline fun shortFun(a:Int, crossinlineout:(Int) -> Unit){
    println("hello")
    out(a)
    println("goodbye")
}

fun main() {
    shortFunc("worldCup") { a -> println("tomorrow") }
    shortFun(3){
        println("a:$it")
        //비지역반환
        return //shortFun out에서 리턴되어 굿바이 출력X
    }
}