package Lesson2.Chapter3

// vararg 변수로 들어온 인자는 1개이상의 변수를 받을 수 있음
fun normalVarargs(vararg a:Int){
    for(num in a){
        print("$num ")
    }
    print(a::class.simpleName) //IntArray
    print("\n")
}

fun main(){
    normalVarargs(1)
    normalVarargs(1,3,5,7,9,11)
}