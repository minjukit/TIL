package Lesson2.Chapter4

var global = 10



fun main(){

    fun localFunc1(){ //지역함수
        println("localfun1")
    }

    localFunc1()



    global = 15
    val local1 =15
    userFunc()
    println(global) //20
    println(local1) //15
}

fun userFunc(){ //최상위 함수
    val local1 = 20
    global = 20
}