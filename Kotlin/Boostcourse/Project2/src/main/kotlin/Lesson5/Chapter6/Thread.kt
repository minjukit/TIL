package Lesson5.Chapter6

//클래스
class SimpleThread : Thread(){
    override fun run() {
        println("class thread ${Thread.currentThread()}")
    }
}

//인터페이스
class SimpleRunnable: Runnable {
    override fun run() {
        println("Interface thread ${Thread.currentThread()}")
    }

}


fun main() {
    val thread = SimpleThread()
    thread.start()

    val runnable = SimpleRunnable()
    val thread2 = Thread(runnable)
    thread2.start()

    // 익명객체
    object:Thread(){
        override fun run() {
            println("object thread ${currentThread()}")
        }
    }.start() //오브젝트니까 바로 실행가능

    //람다식
    Thread{
        println("Lambda thread ${Thread.currentThread()}")
    }


}