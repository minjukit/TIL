package Lesson1.Chapter1

class Birds (var name:String,val wing:Int,var beak:String){ //주생성자

    //부생성자
/*    constructor(_name:String,_wing:Int,_beak:String){
        name=_name
        wing=_wing
        beak=_beak
    }*/
    // 초기화 블럭
    init {
        println("==========init start============")
        name = name.capitalize()
        println("name: $name, wing:$wing, beak:$beak")
        println("==========init end=============")

    }
    fun fly(){
        println("FLY")
    }
}

fun main(){
   val coco = Birds("꼬꼬",2,"long")
    println("${coco.name} ${coco.beak}")
}