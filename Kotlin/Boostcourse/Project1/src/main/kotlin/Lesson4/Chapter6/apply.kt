package Lesson4.Chapter6

import java.io.File

/* apply()
 * 호출하는 객체 T를 이어지는 블럭으로 전달 후 객체 자체 반환
 * also와 비슷하지만 also는 it으로 받고 생략불가 하지만 apply는 this로 받고 생략가능
 * 람다식이 확장함수형식으로 처리한다
 * 객체 초기화시 유용하다
 */

fun main(){

    //1. apply 사용법

    data class Person(var name:String, var skills:String)

    var person = Person("minju","kotlin")

    person.apply {
        this.skills = "swift"
    }
    println(person)// Person(name=minju, skills=swift)

    val returnObj = person.apply {
        //this 생략가능
        name = "kim"
        skills = "objective-c"
    }
    println(person)
    println(returnObj)
    // 둘다 Person(name=kim, skills=objective-c)


    //2. 디렉터리 생성시 apply()
    var path :String ="c:\\22"
    File(path).apply { mkdirs() } //this생략


    //3. 안드로이드 레이아웃 초기화 시 apply()
    //param을 계속 설정해주지 말고 apply로
    /*
    val param = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT).apply{
        gravity = Gravity.CENTER_HORIZONTAL
        weight = 1f
        topMargin = 10
    }
     */


}