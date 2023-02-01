package lv0

class Twice {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 100, -99, 1, 2, 3),
        )

        for (case in testCase) {
            solution(case).forEach { print("$it ") }
            println()
        }
    }

    private fun solution(numbers: IntArray): IntArray {
        return numbers.map { it * 2 }.toIntArray()
    }
}
