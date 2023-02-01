package lv0

class ContinuedSum {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(3, 12),
            intArrayOf(5, 15),
            intArrayOf(4, 14),
            intArrayOf(5, 5),
        )

        for (case in testCase) {
            solution(case[0], case[1]).forEach { print("$it ") }
            println()
        }
    }

    private fun solution(num: Int, total: Int): IntArray {
        val answer = IntArray(num)
        val centerIdx = if (num % 2 == 0) {
            num / 2 - 1
        } else {
            num / 2
        }
        val center = total / num
        for (i in 0 until num) {
            answer[i] = center - centerIdx + i
        }
        return answer
    }
}
