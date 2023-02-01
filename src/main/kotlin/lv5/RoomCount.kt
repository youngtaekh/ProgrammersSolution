package lv5

class RoomCount {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(2, 4, 6, 0, 2, 2, 4, 6, 0),
            intArrayOf(6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0),
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private var answer = 0

    private fun solution(arrows: IntArray): Int {
        answer = 0
        var cur = 0 to 0
        val point = mutableMapOf<Pair<Int, Int>, Int>()
        val path = mutableMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Boolean?>()
        point[cur] = -1
        for (a in arrows) {
            cur = add(path, point, cur, a)
            cur = add(path, point, cur, a)
        }
        return answer
    }

    private fun add(
        path: MutableMap<Pair<Pair<Int, Int>, Pair<Int, Int>>, Boolean?>,
        point: MutableMap<Pair<Int, Int>, Int>,
        cur: Pair<Int, Int>,
        a: Int
    ): Pair<Int, Int> {
        var x = cur.first
        var y = cur.second
        when (a) {
            1, 2, 3 -> x += 1
            5, 6, 7 -> x -= 1
        }
        when (a) {
            0, 1, 7 -> y += 1
            3, 4, 5 -> y -= 1
        }
        val next = x to y
        print("cur: $cur, next: $next ")
        if (point.containsKey(next)) {
            if (!path.containsKey(cur to next) && !path.containsKey(next to cur)) {
                print("Find")
                answer += 1
            }
        } else {
            point[next] = a
        }
        path[cur to next] = true
        path[next to cur] = true
        println(".")
        return x to y
    }
}
