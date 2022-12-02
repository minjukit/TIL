package Lesson3.Chapter5

//일반 람다식 내부에서는 return 이 불가능하며, 인라인 람다식은 가능하다

fun main() {
    retFunc()
}

inline fun inlineLambda(a:Int, b:Int,out:(Int,Int)->Unit){
    out(a,b)
}

fun retFunc(){
    println("start of fun")
    inlineLambda(12,3) lit@{ a,b ->
        val result = a+b
        if(result>10) return@lit //비지역반환이라 라벨표기
        //암묵적 라벨 (그냥 인라인람다이름써주기)
        //if(result>10) return@inlineLambda
        println("10보다작으면 출력")
    }
    println("end of fun")
}