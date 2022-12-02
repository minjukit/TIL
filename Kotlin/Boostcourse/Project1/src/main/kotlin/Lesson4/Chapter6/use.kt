package Lesson4.Chapter6

/* use()
 * use는 객체 사용한 후 close()등 자동적으로 호출해 닫아줌
 * 블럭은 닫힐 수 있는 객체를 지정해야 함
 */

// use()로 파일 닫기

import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

fun main(){
    PrintWriter(FileOutputStream("d:\\test\\s.txt")).use {
        it.print("hello") //txt에 헬로 넣고 파일 닫기 use가 해줌
    }
}