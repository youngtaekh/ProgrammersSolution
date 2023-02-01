package lv0

class PaperCutting {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(2, 2),
            intArrayOf(2, 5),
            intArrayOf(1, 1),
        )

        for (case in testCase) {
            println(solution(case[0], case[1]))
        }
    }

    private fun solution(m: Int, n: Int): Int {
        if (m < n) {
            if (m == 1) {
                return n - 1
            }
            return m - 1 + (n - 1) * m
        } else {
            if (n == 1) {
                return m - 1
            }
            return n - 1 + (m - 1) * n
        }
    }
}
