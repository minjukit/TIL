package Lesson1.Chapter2

fun main(){
    //지수부와 가수부의 공간제약에 따른 부동 소수점 연산의 단점
    var num:Double = 0.1
    for(x in 0..999){
        num += 0.1
    }
    println("sum: $num") //sum: 100.09999999999859


    // 소수점 이동
    val exp01 = 3.14E-2 // 왼쪽으로 소수점 2칸 이동, 0.0314
    val exp02 = 3.14e2  //  오른쪽으로 소수점 2칸 이동, 314.0

    println("1: $exp01 2: $exp02")
}