package Lesson1.Chapter1

import org.intellij.lang.annotations.Language

open class Bird(var name: String, var wing: Int, var beak: String) {
    open fun fly() {
        println("fly~~tothesky~")

    }
}

//주생성자 상속은 클래스명으로
class Lark(name: String, wing: Int, beak: String) : Bird(name, wing, beak) {
    fun singHitone() {
        println("sing Hitone")
    }
}

//부생성자 상속은 super로
class Parrot : Bird {
    var language: String

    constructor(name: String, wing: Int, beak: String, language: String) : super(name, wing, beak) {
        this.language = language
    }

    fun speak() {
        println("speak: $language")
    }

    override fun fly() {
        println("Parrot~fly~~tothesky~")
    }

}

fun main() {
    val lark = Lark("라크", 2, "short")
    val parrot = Parrot("myparrot", 2, "long", "English")
    println("${lark.name}")
    println("${parrot.name}, ${parrot.language}")
    lark.singHitone()
    lark.fly()
    parrot.speak()
    parrot.fly()
}