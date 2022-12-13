package Lesson2.Chapter3

abstract class Vehicle(val name:String, val color:String, val weight:Double) {
    //주 생성자에 정의된 프로퍼티들은 비추상 프로퍼티들이라 초기화 해줘야 됨

    //추상 프로퍼티 - 하위 클래스에서 오버라이딩
    abstract val maxSpeed: Double

    // 비추상 프로퍼티
    var year:String = "2022"

    abstract fun start()

    fun displaySpec(){
        println("name: $name, color: $color")
    }
}

class Car(name: String,
          color: String,
          weight: Double,
          override val maxSpeed: Double) : Vehicle(name, color, weight) {

    override fun start() {
        println("car started")
    }

}
fun main(){

    val car =Car("k", "black", 3333.3,120.0)
    car.year = "2000"
    println(car.displaySpec())
    println(car.maxSpeed)
    println(car.year)
}