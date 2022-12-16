package Lesson3.Chapter4

class Box<T>(a:T){
    var name = a

}

fun main(){
    var box1 = Box(1) //ctrl shift p로 추론된 타입 보기
    val box2= Box("min") //Box<String>
}