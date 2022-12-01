package Lesson1.Chapter2


fun main(){
    /* 비트연산자
    * 4 shl 3   ==    4.shl(3)
    * shl(bits) : 비트만큼 왼쪽 이동(부호있음)
    * shr(bits) : 비트만큼 오른쪽 이동(부호있음)
    * ushr(bits) : 비트만큼 오른쪽 이동(부호없음)
    * and(bits) : 논리곱
    * or(bits) : 논리합
    * xor(bits) : 배타 연산
    * inv() : 비트 뒤집기
    */

    val x = 4 // 0100(2) 4(10)
    val y = 0b0000_1010 // 5(10)
    val z = 0x0f //0b0000_1111(2) 15(10)

    println("x shl 2 : ${x.shl(2)}") // 16(10) 0001_0000(2)
    println("x inv : ${x.inv()}") // 중위 표현식 x inv 안됨 // -5

}