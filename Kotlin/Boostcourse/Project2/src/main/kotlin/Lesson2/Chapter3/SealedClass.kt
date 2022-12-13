package Lesson2.Chapter3

sealed class Result{
    open class Success(val msg:String):Result()
    open class Error(val code:Int, val msg:String):Result()

}

fun main(){
    val result =Result.Success("Success")
    println(eval(result))
}

fun eval(result:Result):String = when(result){
    is Result.Success -> result.msg
    is Result.Error -> result.msg
    //else 생략 가능
}

/*
enum class DayOfWeek(val num:Int){
    MONDAY(1), TUESDAY(2), SUNDAY(7)
}
fun main(){
    val day = DayOfWeek.SUNDAY
    when(day.num){
        1,2,3,4,5 -> println("weekday")
        6,7-> println("weekend")
    }
}*/
