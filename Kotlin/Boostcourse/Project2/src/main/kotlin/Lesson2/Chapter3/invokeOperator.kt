package Lesson2.Chapter3

class Manager{
    operator fun invoke(value:String){
        println(value)
    }
}

fun main(){
    val manager = Manager()
    manager("manager.invoke(..)형태로 호출되며 invoke가 생략됨")

    val sum = {x:Int,y:Int -> x+y}
    sum.invoke(3,10)
    println(sum(3,10))
}