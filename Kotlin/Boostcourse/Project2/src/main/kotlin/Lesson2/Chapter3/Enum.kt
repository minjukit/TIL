package Lesson2.Chapter3

interface Score{
    fun getScore():Int
}

enum class MemberType(var prio:String):Score{
    NORMAL("Third"){
        override fun getScore(): Int {
            return 100
        }
    },
    SILVER("Second"){
        override fun getScore(): Int {
            return 1000
        }
    },
    GOLD("First"){
        override fun getScore() = 10000
    }
}

fun main(){
    println(MemberType.GOLD.getScore())
    println(MemberType.GOLD)
    println(MemberType.valueOf("SILVER"))
    println(MemberType.NORMAL.prio)

}