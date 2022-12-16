package Lesson3.Chapter4

import java.util.*

fun main(){
    val b= Array(10,{0})
    println(b.contentToString())

    val arr= arrayOf(4,5,6,7,1,2,0)
    val sortedArr = arr.sortedArray()
    println(sortedArr.contentToString())
    arr.sortDescending()
    println(Arrays.toString(arr))

    val items = arrayOf("dog","cat","lion","kangaroo")
    items.sortBy{it.length}
    println(items.contentToString())
}