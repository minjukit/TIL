package Final

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.system.exitProcess

/*
3 x 3 격자 공간에 가로, 세로 대각선으로 같은 게임말 3개를 나란히 놓을 경우 점수를 획득하며 이기게 되는 게임입니다.

게임 말은 O와 X로 두명의 플레이어가 경쟁하게 됩니다.

틱택도 판은 3 x 3의 격자 공간으로 준비합니다.
비어있는 공간에 말을 놓을 수 있으며 Player 1과 Player 2가 번갈아 가면서 말을 놓습니다.
콘솔을 통해 말을 놓을 위치를 y, x 형태로 입력 받아서 말을 그려줍니다.
이때 비어 있지 않은 공간에는 말을 새롭게 그릴 수 없습니다.
 */

var board = Array<Array<String>>(3) { Array<String>(3) { "" } }
val br = BufferedReader(InputStreamReader(System.`in`))
var player = 1
var turn =0
var x: Int = 0
var y: Int = 0
fun main() {
    play()
}

fun initBoard() {
    for (i in 0..2) {
        for (j in 0..2) {
            board[i][j] = " "
        }
    }
}

fun printBoard() {
    println(" 0 1 2")
    println("0${board[0][0]}|${board[0][1]}|${board[0][2]}")
    println(" -+-+-")
    println("1${board[1][0]}|${board[1][1]}|${board[1][2]}")
    println(" -+-+-")
    println("2${board[2][0]}|${board[2][1]}|${board[2][2]}")
}

val isInRange = { x: Int, y: Int -> x in 0..2 && y in 0..2 }

/*
(x:Int,y:Int):Boolean{
    if(x in 0..2 &&y in 0..2) return true
    return false
}
*/
fun isValid(x: Int, y: Int): Boolean {
    return isInRange(y, x) && board[y][x] == " "
}

fun playerInput() {
    player = if (player == 0) 1 else 0
    print("Player $player 입력(줄,칸): ")
    val st = StringTokenizer(br.readLine(), ",")
    y = st.nextToken().toInt()
    x = st.nextToken().toInt()
    if (isValid(y, x)) board[y][x] = player.toString()
}

fun isWin(): Boolean {

    if (board[y][0] == board[y][1] && board[y][1] == board[y][2]) return true
    if (board[0][x] == board[1][x] && board[1][x] == board[2][x]) return true
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != " ") return true
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != " ") return true

    return false
}

fun play() {
    println("1번째 턴\n")
    initBoard()
    printBoard()

    while (true) {
        println("${turn+1}번째 턴\n")
        playerInput()
        printBoard()
        if (isWin()) {
            println("Player $player 승리!")
            exitProcess(0)
        }
    }

}