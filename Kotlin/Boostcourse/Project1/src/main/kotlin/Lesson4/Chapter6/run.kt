package Lesson4.Chapter6

/* run()
 * 사용 방법 2가지
 * 1. 인자가 없는 익명함수처럼 동작하는 형태
 * 2. 객체에서 호출하는 형태
 * 마지막 표현식이 반환된다
 */

fun main() {

    //1. apply와 run 비교

    data class Person(var name: String, var skills: String)

    var person = Person("minju", "kotlin")

    val returnObj = person.apply {
        name = "kim"
        skills = "objective-c"
        "success" //무의미
    }
    println(person)
    println(returnObj)// Person(name=kim, skills=objective-c)

    val returnObj2 = person.run {
        name = "juju"
        skills = "c++"
        "success"
    }
    println(person) //Person(name=juju, skills=c++)
    println(returnObj2)// success



}