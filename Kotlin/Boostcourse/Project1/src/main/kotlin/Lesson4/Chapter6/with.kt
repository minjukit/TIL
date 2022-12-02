package Lesson4.Chapter6

/* with()
 * 인자로 받는 객체를 블럭의 receiver로 전달하고 결과값을 반환
 * run()과 기능이 거의 동일, but with()은 리시버로 전달할 객체를 처리한다
 * this 생략가능
 * 마지막 줄 반환
 */

fun main() {

    //1. with() 사용법
    data class User(var name: String, var skills: String, var email:String? = null)

    var user = User("minju", "kotlin")

    val result = with(user) {
        skills = "objective-c"
        email = "minju@test.com"
        //마지막에 반환식이 없으면 Unit 반환
    }
    println(user) //User(name=minju, skills=objective-c, email=minju@test.com)
    println("result: $result") //result: kotlin.Unit


    //2.  with()는  safe-call 지원하지 않기 때문에 let 같이 사용한다
    /*
    supportActionBar?.let {
        with(it) {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
        }
    }*/
}