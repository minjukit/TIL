package Lesson3.Chapter4

open class Parent

class Child:Parent()
class Cup<T>


fun main(){
    //val obj1:Child=Parent() //하위클래스 객체에 상위 클래스 할당 시 type mismatch
    val obj2 = Child()
    val obj3 = Cup<Parent>()
    // val obj4:<Parent> = Cup<Child>() //형식 매개변수도 반대도 type mismatch
}