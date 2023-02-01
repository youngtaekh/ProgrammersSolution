package lv0

import kotlin.math.abs

class FindParallel {
    fun start() {
        val testCase = arrayOf(
            arrayOf(
                intArrayOf(1, 4), intArrayOf(9, 2), intArrayOf(3, 8), intArrayOf(11, 6),
            ),
            arrayOf(
                intArrayOf(3, 5), intArrayOf(4, 1), intArrayOf(2, 4), intArrayOf(5, 10),
            ),
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private fun solution(dots: Array<IntArray>): Int {
        //(1 2), (3 4)
        var ax = abs(dots[0][0] - dots[1][0])
        var ay = abs(dots[0][1] - dots[1][1])
        var bx = abs(dots[2][0] - dots[3][0])
        var by = abs(dots[2][1] - dots[3][1])
        println(ax.toDouble()/ay.toDouble())
        println(bx.toDouble()/by.toDouble())
        if (ax.toDouble()/ay.toDouble() == bx.toDouble()/by.toDouble()) {
            return 1
        }
        //(1 3), (2 4)
        ax = abs(dots[0][0] - dots[2][0])
        ay = abs(dots[0][1] - dots[2][1])
        bx = abs(dots[1][0] - dots[3][0])
        by = abs(dots[1][1] - dots[3][1])
        println(ax.toDouble()/ay.toDouble())
        println(bx.toDouble()/by.toDouble())
        if (ax.toDouble()/ay.toDouble() == bx.toDouble()/by.toDouble()) {
            return 1
        }
        //(1 4), (2 3)
        ax = abs(dots[0][0] - dots[3][0])
        ay = abs(dots[0][1] - dots[3][1])
        bx = abs(dots[1][0] - dots[2][0])
        by = abs(dots[1][1] - dots[2][1])
        println(ax.toDouble()/ay.toDouble())
        println(bx.toDouble()/by.toDouble())
        if (ax.toDouble()/ay.toDouble() == bx.toDouble()/by.toDouble()) {
            return 1
        }
        return 0
    }
}
