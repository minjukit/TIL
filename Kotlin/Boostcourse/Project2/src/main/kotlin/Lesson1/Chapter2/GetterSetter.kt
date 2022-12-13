package Lesson1.Chapter2

class User(_id:Int,_name:String,_age:Int){
    val id:Int = _id
        get() = field //id쓰면 무한재귀라 field로 참조

    var name:String = _name

    var age:Int = _age
        set(value) {
            field = value +1
        }
}

fun main(){
    val user = User(1,"minju",24)
    println(user.age)//24
    user.age = 24
    println(user.age)//25
}