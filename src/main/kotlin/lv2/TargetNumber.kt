package lv2

class TargetNumber {
    fun start() {
        val numbers = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(4, 1, 2, 1)
        )
        val targets = intArrayOf(3, 4)

        for (i in numbers.indices) {
            println(solution(numbers[i], targets[i]))
        }
    }

    var answer = 0

    fun solution(numbers: IntArray, target: Int): Int {
        answer = 0
        dfs(numbers, target, 0, 0)
        return answer
    }

    private fun dfs(source: IntArray, target: Int, sum: Int, index: Int) {
        if (index == source.size) {
            if (sum == target) {
                answer += 1
            }
            return
        }
        dfs(source, target, sum + source[index], index + 1)
        dfs(source, target, sum - source[index], index + 1)
    }
}
