package Lesson1.Chapter2
import kotlin.properties.Delegates

class Person{
    var name: String by Delegates.observable("NONAME"){
            prop, old, new->
        println("$old -> $new") // 값이 변경될때마다 출력
    }
}

fun main(){
    val person = Person()
    person.name="forky"
    person.name="gabby"
}