package lv3

class DisappearTile {

    /**
    플레이어 A와 플레이어 B가 서로 게임을 합니다. 당신은 이 게임이 끝날 때까지 양 플레이어가 캐릭터를 몇 번 움직이게 될지 예측하려고 합니다.

    각 플레이어는 자신의 캐릭터 하나를 보드 위에 올려놓고 게임을 시작합니다. 게임 보드는 1x1 크기 정사각 격자로 이루어져 있으며, 보드 안에는 발판이
    있는 부분과 없는 부분이 있습니다. 발판이 있는 곳에만 캐릭터가 서있을 수 있으며, 처음 캐릭터를 올려놓는 곳은 항상 발판이 있는 곳입니다. 캐릭터는
    발판이 있는 곳으로만 이동할 수 있으며, 보드 밖으로 이동할 수 없습니다. 밟고 있던 발판은 그 위에 있던 캐릭터가 다른 곳으로 이동하여 다른 발판을
    밞음과 동시에 사라집니다. 양 플레이어는 번갈아가며 자기 차례에 자신의 캐릭터를 상하좌우로 인접한 4개의 칸 중에서 발판이 있는 칸으로 옮겨야 합니다.

    다음과 같은 2가지 상황에서 패자와 승자가 정해지며, 게임이 종료됩니다.

    움직일 차례인데 캐릭터의 상하좌우 주변 4칸이 모두 발판이 없거나 보드 밖이라서 이동할 수 없는 경우, 해당 차례 플레이어는 패배합니다.
    두 캐릭터가 같은 발판 위에 있을 때, 상대 플레이어의 캐릭터가 다른 발판으로 이동하여 자신의 캐릭터가 서있던 발판이 사라지게 되면 패배합니다.
    게임은 항상 플레이어 A가 먼저 시작합니다. 양 플레이어는 최적의 플레이를 합니다. 즉, 이길 수 있는 플레이어는 최대한 빨리 승리하도록 플레이하고,
    질 수밖에 없는 플레이어는 최대한 오래 버티도록 플레이합니다. '이길 수 있는 플레이어'는 실수만 하지 않는다면 항상 이기는 플레이어를 의미하며,
    '질 수밖에 없는 플레이어'는 최선을 다해도 상대가 실수하지 않으면 항상 질 수밖에 없는 플레이어를 의미합니다. 최대한 오래 버틴다는 것은 양 플레이어가
    캐릭터를 움직이는 횟수를 최대화한다는 것을 의미합니다.

     1 움직일 곳이 없으면 패배
     2 갈 곳이 하나 뿐

    1 발판이 없어지면 패배
     2 a b 가 같은 곳에 있다



     */

    private lateinit var board: Array<IntArray>
    private lateinit var aloc: IntArray
    private lateinit var bloc: IntArray
    private val dx = intArrayOf(0, 1, 0, -1)
    private val dy = intArrayOf(-1, 0, 1, 0)

    fun start() {
        val boards = mutableListOf<Array<IntArray>>()
        val alocs = mutableListOf<IntArray>()
        val blocs = mutableListOf<IntArray>()

//        boards.add(
//            arrayOf(
//                intArrayOf(1, 1, 1, 1, 1),
//                intArrayOf(1, 1, 1, 1, 1),
//                intArrayOf(1, 1, 1, 1, 1),
//                intArrayOf(1, 1, 1, 1, 1),
//                intArrayOf(1, 1, 1, 1, 1)
//            )
//        )
//        alocs.add(intArrayOf(2, 0))
//        blocs.add(intArrayOf(2, 4))

//        boards.add(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)))
//        alocs.add(intArrayOf(1, 0))
//        blocs.add(intArrayOf(1, 2))

        boards.add(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)))
        alocs.add(intArrayOf(1, 0))
        blocs.add(intArrayOf(1, 2))
////
//        boards.add(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)))
//        alocs.add(intArrayOf(1, 0))
//        blocs.add(intArrayOf(1, 2))
//
//        boards.add(arrayOf(intArrayOf(1, 1, 1, 1, 1)))
//        alocs.add(intArrayOf(0, 0))
//        blocs.add(intArrayOf(0, 4))
//
//        boards.add(arrayOf(intArrayOf(1)))
//        alocs.add(intArrayOf(0, 0))
//        blocs.add(intArrayOf(0, 0))

        for (i in boards.indices) {
            println("index $i - ${solution2(boards[i], alocs[i], blocs[i])}")
        }
    }

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        return 0
    }

    private fun turnProcess(turn: Int, aWin: Boolean = true, bWin: Boolean = true): Int {
        println("Turn: $turn, a win: $aWin, b win: $bWin")
        printBoard()
        val aTurn = turn%2 == 0
        if (!checkEnd(aTurn)) {
            val direction = chooseDirection(aTurn, if (aTurn) aWin else bWin)
            if (aTurn) {
                // 기존의 발판 없애기
                board[aloc[0]][aloc[1]] = 0
                aloc[0] += dy[direction]
                aloc[1] += dx[direction]
            } else {
                // 기존의 발판 없애기
                board[bloc[0]][bloc[1]] = 0
                bloc[0] += dy[direction]
                bloc[1] += dx[direction]
            }
            return turnProcess(turn+1, aWin, bWin)
        }
        return turn
    }

    private fun chooseDirection(aTurn: Boolean, toWin: Boolean = true): Int {
        val loc = if (aTurn) aloc else bloc
        var max = 0
        var direction = -1
        var min = 16
        var minDirection = -1
        val horizon = if (aTurn) bloc[1]-aloc[1] else aloc[1]-bloc[1]
        val vertical = if (aTurn) bloc[0]-aloc[0] else aloc[0]-bloc[0]
        val weight = intArrayOf(10-vertical, 10+horizon, 10+vertical, 10-horizon)

        for (i in 0 until 4) {
            val x = loc[1] + dx[i]
            val y = loc[0] + dy[i]
            if (x >= 0 && x < board[0].size && y >= 0 && y < board.size && board[y][x] == 1) {
                if (max < weight[i]) {
                    max = weight[i]
                    direction = i
                }
                if (min > weight[i]) {
                    min = weight[i]
                    minDirection = i
                }
            }
        }

        return if (toWin) {
            direction
        } else {
            minDirection
        }
    }

    private fun checkEnd(aTurn: Boolean): Boolean {
        return if (aTurn) {
            checkNext(aloc) == 0 || board[aloc[0]][aloc[1]] == 0
        } else {
            checkNext(bloc) == 0 || board[bloc[0]][bloc[1]] == 0
        }
    }

    private fun checkNext(loc: IntArray): Int {
        var value = 0
        if (loc[0] != 0 && board[loc[0]-1][loc[1]] == 1) {
            value += 8
        }
        if (loc[1] != board[0].size-1 && board[loc[0]][loc[1]+1] == 1) {
            value += 4
        }
        if (loc[0] != board.size-1 && board[loc[0]+1][loc[1]] == 1) {
            value += 2
        }
        if (loc[1] != 0 && board[loc[0]][loc[1]-1] == 1) {
            value += 1
        }
        return value
    }

    private fun printBoard() {
        for (i in board.indices) {
            val intArray = board[i]
            for (j in intArray.indices) {
                if (aloc[0] == i && aloc[1] == j && bloc[0] == i && bloc[1] == j) {
                    print("ab ")
                } else if (aloc[0] == i && aloc[1] == j) {
                    print(" a ")
                } else if (bloc[0] == i && bloc[1] == j) {
                    print(" b ")
                } else {
                    print(" ${board[i][j]} ")
                }
            }
            println()
        }
        println("===============")
//        print("a[")
//        for (a in aloc) {
//            print("$a ")
//        }
//        println("\b]")
//        print("b[")
//        for (b in bloc) {
//            print("$b ")
//        }
//        println("\b]")
    }

    var n = 0
    var m = 0

    fun OOB(x: Int, y: Int): Boolean {
        return x < 0 || x >= n || y < 0 || y >= m
    }

    var vis = Array(5) { BooleanArray(5) } // 방문 여부(0일 경우 해당 칸이 아직 남아있음을 의미)
//    var cur = Array(5) { Array(5) { " " } }
    var block = Array(5) { IntArray(5) }

    // 현재 상태에서 둘 다 최적의 플레이를 할 때 남은 이동 횟수
    // 반환 값이 짝수 : 플레이어가 패배함을 의미, 홀수 : 플레이어가 승리함을 의미
    // curx, cury : 현재 플레이어의 좌표, opx, opy : 상대 플레이어의 좌표
    fun solve(curx: Int, cury: Int, opx: Int, opy: Int, count: Int): Int {
        // 플레이어가 밟고 있는 발판이 사라졌다면
        if (vis[curx][cury]) return 0
        var ret = 0
        // 플레이어를 네 방향으로 이동시켜 다음 단계로 진행할 예정
        for (dir in 0..3) {
            val nx = curx + dx[dir]
            val ny = cury + dy[dir]
            if (OOB(nx, ny) || vis[nx][ny] || block[nx][ny] == 0) continue
            vis[curx][cury] = true // 플레이어가 직전에 있던 곳에 방문 표시를 남김

            // 플레이어를 dir 방향으로 이동시켰을 때 턴의 수
            // 다음 함수를 호출할 때 opx, opy, nx, ny 순으로 호출해야 함에 주의
            val `val` = solve(opx, opy, nx, ny, count + 1) + 1

            // 방문 표시 해제
            vis[curx][cury] = false

            // 1. 현재 저장된 턴은 패배인데 새로 계산된 턴은 승리인 경우
            if (ret % 2 == 0 && `val` % 2 == 1) ret = `val` // 바로 갱신
            else if (ret % 2 == 0 && `val` % 2 == 0) ret = Math.max(ret, `val`) // 최대한 늦게 지는걸 선택
            else if (ret % 2 == 1 && `val` % 2 == 1) ret = Math.min(ret, `val`) // 최대한 빨리 이기는걸 선택
        }
        return ret
    }

    fun solution2(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        n = board.size
        m = board[0].size
        vis = Array(n) { BooleanArray(m) }
        block = Array(n) { IntArray(m) }
        for (i in 0 until n) for (j in 0 until m) block[i][j] = board[i][j]
        return solve(aloc[0], aloc[1], bloc[0], bloc[1], 0)
    }
}
