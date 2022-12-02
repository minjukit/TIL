package Lesson4.Chapter6

/* let()
 * 다른 메서드를 실행하거나 연산을 수행하는 경우
 * 매개변수 람다 블럭은 T를 매개변수로 받아 R 반환
 */

fun main() {

    //1.  let으로 null check
    val score: Int? = 32

    //normal null check
    fun checkScore(){
        if (score != null) println("score: $score")
    }
    //let + safe-call
    fun checkScoreLet(){
        score?.let {
            println("score: $it") //it은 let으로 벗긴 값을 복사한다
            val str = score.let { it.toString() }
            println(str)
        }
    }
    checkScore()
    checkScoreLet()

    //2. let으로 else문 대체
    val name:String? = null
    name?.let { println("name: $it") } ?: println("null") // elvis


    //2. let 함수의 체이닝
    var a= 1
    var b=2
    a = a.let { it+2 }.let {
        println("a = $a") //1
        val i = it +b // 3 + 2
        i
    }
    println(a) // 5

    //3. let 중첩 사용
    var x = "kotlin"
    x= x.let { outer->
        outer.let {  inner ->
            println("Inner is $inner and outer is $outer") //2개이상일 경우 it 사용하지 않고 명시적 이름
            "inner str" //반환 되지 않음
        }
        "outer str"//x에 할당
    }
    println(x)

    //4. 안드로이드 커스텀 뷰에서 let 활용
    // 만약 패딩 설정넣을때 변수 선언하여서 설정하는 것보다 메모리 안쓰고! let으로
    /*
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16f,
    resources.displayMetrics).toInt().let{
        setPadding(it,0,it,0) //padding 을 it으로 받음
    }
     */
}