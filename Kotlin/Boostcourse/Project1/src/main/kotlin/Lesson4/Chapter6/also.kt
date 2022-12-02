package Lesson4.Chapter6

import java.io.File

/*
 * also()
 * 호출 하는 객체 T를 이어지는 블럭에 전달하고 T객체 반환
 * 블록안의 코드 수행결과와 상관 없이 객체 반환
 */

fun main() {


    //1. also와 let 비교교
    data class Person(var name:String, var skills:String)

    var person = Person("minju","kotlin")

    val a = person.let {
        it.skills = "android"
        "success"//마지막 문장 결과로 반환
    }
    println(person) //Person(name=minju, skills=android)
    println("a: $a") //a: success

    val b= person.also {
        it.skills = "java"
        "success" //마지막 반환안하니까 무의미한 코드
    }
    println(person) //Person(name=minju, skills=java)
    println("b: $b") //b: Person(name=minju, skills=java)
    // also는 본인을 반환함

    // 2. 디렉터리 생성 시 let과 also 사용
    fun makeDir(path:String) = path.let { File(it) }.also { it.mkdirs() }
    //파일 만들고 디렉터리 생성하고 그 결과파일 반환됨
}