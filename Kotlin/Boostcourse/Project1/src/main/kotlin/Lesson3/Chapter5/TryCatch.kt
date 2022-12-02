package Lesson3.Chapter5

fun main() {
    val a= 6
    val b = 0
    val c:Int
    try {
        c = a/b
        println("여기는 실행안됨 이미 e발생")
    }catch (e:Exception){
        println(e.message)
    }finally {
        print("fin")
    }

    // 예외 의도적으로 발생시키기
    // throw Exception(message: String)
}