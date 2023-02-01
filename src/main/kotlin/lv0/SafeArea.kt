package lv0

class SafeArea {
    fun start() {
        val testCase = arrayOf(
            arrayOf(
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0),
            ),
            arrayOf(
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 1, 0),
                intArrayOf(0, 0, 0, 0, 0),
            ),
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
            )
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private fun solution(board: Array<IntArray>): Int {
        var answer = 0
        val size = board.size - 1
        for (i in 0..size) {
            for (j in 0 .. size) {
                if (i != 0 && j != 0 && board[i-1][j-1] == 1) continue
                if (i != 0 && board[i-1][j] == 1) continue
                if (i != 0 && j != size && board[i-1][j+1] == 1) continue

                if (j != 0 && board[i][j-1] == 1) continue
                if (board[i][j] == 1) continue
                if (j != size && board[i][j+1] == 1) continue

                if (i != size && j != 0 && board[i+1][j-1] == 1) continue
                if (i != size && board[i+1][j] == 1) continue
                if (i != size && j != size && board[i+1][j+1] == 1) continue
                answer++
            }
        }
        return answer
    }
}
