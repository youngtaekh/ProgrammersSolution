package lv3

import kotlin.math.max

class Tiling {
    /**
     * 0. xx
     *     x
     *
     * 1.  x
     *    xx
     *
     * 2. x
     *    xx
     *
     * 3. xx
     *    x
     *
     * 4. xxx
     *
     * 5. x
     *    x
     *    x
     */
    fun start() {
        val list = mutableListOf<Int>()
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        list.add(6)
        list.add(7)
        list.add(8)
        var startTime: Long
        for (n in list) {
            startTime = System.currentTimeMillis()
            print("$n -> ${solveLoop(n)}, ")
            if (n < answerList.size) {
                println(answerList[n])
            } else {
                println(solution(n))
            }
        }
    }

    private val answerList = intArrayOf(1, 1, 3, 10, 23, 62, 170, 441, 1173, 3127, 8266, 21937, 58234)
    private val number = 1_000_000_007
    private val numberLong = 1_000_000_007L
    private val tiles = arrayOf(
        arrayOf(intArrayOf(0,0), intArrayOf(0, 1), intArrayOf(1, 1)),
        arrayOf(intArrayOf(0,0), intArrayOf(0, 1), intArrayOf(-1, 1)),
        arrayOf(intArrayOf(0,0), intArrayOf(1, 0), intArrayOf(1, 1)),
        arrayOf(intArrayOf(0,0), intArrayOf(0, 1), intArrayOf(1, 0)),
        arrayOf(intArrayOf(0,0), intArrayOf(0, 1), intArrayOf(0, 2)),
        arrayOf(intArrayOf(0,0), intArrayOf(1, 0), intArrayOf(2, 0))
    )
    private lateinit var map: Array<BooleanArray>
    private val moveX = intArrayOf(0, 1, 0, -1)
    private val moveY = intArrayOf(-1, 0, 1, 0)

    fun solution(n: Int): Int {
        map = Array(3) { BooleanArray(n) { false } }
        return solve(0, 0) % number
//        return solveLoop(n)
    }

    fun solveLoop(n: Int): Int {
        val list = mutableListOf(1L, 1L, 3L, 10L)
        val h = LongArray(3)
        for (i in list.size .. n) {
            list.add(list[i - 1] + (list[i - 2] * 2) + (list[i - 3] * 5))
            h[(i - 4) % 3] = (h[(i - 4) % 3] + list[i - 4]) % numberLong
            list[i] += (h[0] + h[1] + h[2] + h[i % 3]) * 2
            list[i] %= numberLong
        }
        return list[n].toInt()
    }

    fun solve(x: Int, y: Int, count: Int = 0): Int {
        var dx = x
        var dy = y
        if (dx == 3) {
            dy += 1
            dx = 0
        }
        if (map[0].size == count) {
            return 1
        }
        if (dy == map[0].size) {
            return 0
        }
        var answer = 0
        for (tile in tiles) {
            if (map[dx][dy]) {
                break
            }
            if (addCheck(dx, dy, tile)) {
                addTile(dx, dy, tile)
                answer += solve(dx+1, dy, count+1)
                answer %= number
                subtractTile(dx, dy, tile)
            }
        }
        // check next point
        if (dx != map.size-1 || dy != map[0].size-1) {
            answer += solve(dx+1, dy, count)
            answer %= number
        }
        return answer
    }

    fun addCheck(x: Int, y: Int, tile: Array<IntArray>): Boolean {
        for (point in tile) {
            val dx = x+point[0]
            val dy = y+point[1]
            if (dx >= map.size || dx < 0 || dy >= map[0].size || map[dx][dy]) {
                return false
            }
        }
        return true
    }

    fun addTile(x: Int, y: Int, tile: Array<IntArray>) {
        for (point in tile) {
            val dx = x+point[0]
            val dy = y+point[1]
            map[dx][dy] = true
        }
    }

    fun subtractTile(x: Int, y: Int, tile: Array<IntArray>) {
        for (point in tile) {
            val dx = x+point[0]
            val dy = y+point[1]
            map[dx][dy] = false
        }
    }
}
