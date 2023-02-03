package lv5

class RoomCount {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(2, 4, 6, 0, 2, 2, 4, 6, 0),
            intArrayOf(6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0),
        )

        for (case in testCase) {
            println(solution(case))
            println(solution2(case))
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

    private fun solution2(arrows: IntArray): Int {
        var answer = 0
        var cPoint = 0 to 0
        val points = mutableListOf(cPoint)
        //draw lines
        arrows.forEach {
            when (it) {
                0 -> {
                    cPoint = cPoint.first to cPoint.second+1
                    points.add(cPoint)
                }
                1 -> {
                    cPoint = cPoint.first+1 to cPoint.second+1
                    points.add(cPoint)
                }
                2 -> {
                    cPoint = cPoint.first+1 to cPoint.second
                    points.add(cPoint)
                }
                3 -> {
                    cPoint = cPoint.first+1 to cPoint.second-1
                    points.add(cPoint)
                }
                4 -> {
                    cPoint = cPoint.first to cPoint.second-1
                    points.add(cPoint)
                }
                5 -> {
                    cPoint = cPoint.first-1 to cPoint.second-1
                    points.add(cPoint)
                }
                6 -> {
                    cPoint = cPoint.first-1 to cPoint.second
                    points.add(cPoint)
                }
                7 -> {
                    cPoint = cPoint.first-1 to cPoint.second+1
                    points.add(cPoint)
                }
            }
        }
        points.forEach { println(it) }
        println("----")
        //find connected line
        val paths = mutableMapOf<Pair<Int, Int>, MutableList<Pair<Int, Int>>>()
        points.mapIndexed { index, pair ->
            if (paths[pair] == null) {
                paths[pair] = mutableListOf()
            }
            if (index != 0) {
                paths[pair]!!.add(points[index - 1])
            }
            if (index != points.size - 1) {
                paths[pair]!!.add(points[index + 1])
            }
        }
        paths.forEach { println(it) }

        val closedAreaList = mutableListOf<MutableList<Pair<Int, Int>>>()
        points
            .filterIndexed { index, pair ->
                index == 0 ||
                        index == points.size - 1 ||
                        pair.first - points[index-1].first != points[index+1].first - pair.first ||
                        pair.second - points[index-1].second != points[index+1].second - pair.second
            }
            .map {
                val closedArea = mutableListOf(it)
                var check = true
                while (check) {
                    var nextPoint: Pair<Int, Int>? = null
                    for (point in paths[it]!!) {
                        if (closedArea.size > 1 && closedArea[closedArea.size-2] != point) {
                            nextPoint = point
                        }
                    }
                    if (nextPoint == it) {
                        check = false
                    } else if (arrows.size < closedArea.size) {
                        check = false
                    }
                }
            }
        return answer
    }
}
