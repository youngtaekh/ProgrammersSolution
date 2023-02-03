package lv2

class CrossStar {
    fun start() {
        val testCase = arrayOf(
            //Ax + By + C = 0
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(2, 4, 6),
                intArrayOf(1, 0, -2),
                intArrayOf(1, 2, -4),
                intArrayOf(0, 0, -2),
                intArrayOf(0, 1, -3),
                intArrayOf(1, 0, -3),
                intArrayOf(2, 3, -6),
            ),
            arrayOf(
                intArrayOf(2, -1, 4),
                intArrayOf(-2, -1, 4),
                intArrayOf(0, -1, 1),
                intArrayOf(5, -8, -12),
                intArrayOf(5, 8, 12),
            ),
            arrayOf(
                intArrayOf(0, 1, -1),
                intArrayOf(1, 0, -1),
                intArrayOf(1, 0, 1)
            ),
            arrayOf(
                intArrayOf(1, -1, 0),
                intArrayOf(2, -1, 0),
            ),
            arrayOf(
                intArrayOf(1, -1, 0),
                intArrayOf(2, -1, 0),
                intArrayOf(4, -1, 0),
            )
        )

        for (case in testCase) {
            solution(case).forEach { println(it) }
        }
    }

    fun solution(line: Array<IntArray>): Array<String> {
        //Ax + By + C = 0
        val coordinates = mutableListOf<LongArray>()
        var left: Int? = null
        var right: Int? = null
        var bottom: Int? = null
        var top: Int? = null
        for (i in 0 until line.size - 1) {
            for (j in i + 1 until line.size) {
                val one = line[i]
                val two = line[j]
                val coordinate = findCrossInt(one, two)
                if (coordinate != null) {
                    coordinates.add(coordinate)
                    if (left == null || left > coordinate[0]) {
                        left = coordinate[0].toInt()
                    }
                    if (right == null || right < coordinate[0]) {
                        right = coordinate[0].toInt()
                    }
                    if (bottom == null || bottom > coordinate[1]) {
                        bottom = coordinate[1].toInt()
                    }
                    if (top == null || top < coordinate[1]) {
                        top = coordinate[1].toInt()
                    }
                    println("(${coordinate[0]}, ${coordinate[1]})")
                } else {
                    println()
                }
            }
        }
        val answer = Array(top!! - bottom!! + 1) { CharArray(right!! - left!! + 1) { '.' } }
        for (coordinate in coordinates) {
            answer[top - coordinate[1].toInt()][coordinate[0].toInt() - left!!] = '*'
        }
        return answer.map { String(it) }.toTypedArray()
    }

    private fun findCrossInt(one: IntArray, two: IntArray): LongArray? {
        val a1 = one[0].toLong()
        val b1 = one[1].toLong()
        val c1 = one[2].toLong()
        val a2 = two[0].toLong()
        val b2 = two[1].toLong()
        val c2 = two[2].toLong()
        println("${a1}X + ${b1}Y + $c1")
        println("${a2}X + ${b2}Y + $c2")
        if ((a1 == 0L && b1 == 0L) || (a2 == 0L && b2 == 0L) ||
            (a1 == 0L && a2 == 0L) || (b1 == 0L && b2 == 0L) ||
            b2 * a1 - b1 * a2 == 0L) {
            return null
        }

        if (a1 == 0L && b2 == 0L) {
            if (c2 % a2 == 0L && c1 % b1 == 0L) {
                return longArrayOf(c2 / a2 * -1L, c1 / b1 * -1L)
            }
        } else if (b1 == 0L && a2 == 0L) {
            if (c1 % a1 == 0L && c2 % b2 == 0L) {
                return longArrayOf(c1 / a1 * -1, c2 / b2 * -1)
            }
        } else if (b1 == 0L) {
            if (c1 % a1 == 0L && (a2 * c1 / a1 * -1L + c2) % b2 == 0L) {
                return longArrayOf(c1 / a1 * -1, (a2 * c1 / a1 * -1L + c2) / b2 * -1L)
            }
        } else if (b2 == 0L) {
            if (c2 % a2 == 0L && (a1 * c2 / a2 * -1L + c1) % b1 == 0L) {
                return longArrayOf(c2 / a2 * -1, (a1 * c2 / a2 * -1L + c1) / b1 * -1L)
            }
        } else if (a1 == 0L) {
            if (c1 % b1 == 0L && (b2 * c1 / b1 * -1 + c2) % a2 == 0L) {
                return longArrayOf((b2 * c1 / b1 * -1 + c2) / a2 * -1L, c1 / b1 * -1L)
            }
        } else if (a2 == 0L) {
            if (c2 % b2 == 0L && (b1 * c2 / b2 * -1 + c1) % a1 == 0L) {
                return longArrayOf((b1 * c2 / b2 * -1 + c1) / a1 * -1L, c2 / b2 * -1L)
            }
        } else {
            if ((b1 * c2 - b2 * c1) % (b2 * a1 - b1 * a2) == 0L &&
                (a1 * (b1 * c2 - b2 * c1) / (b2 * a1 - b1 * a2) + c1) % b1 == 0L) {
                return longArrayOf(
                    (b1 * c2 - b2 * c1) / (b2 * a1 - b1 * a2),
                    (a1 * (b1 * c2 - b2 * c1) / (b2 * a1 - b1 * a2) + c1) / b1 * -1L
                )
            }
        }
        return null
    }
}
