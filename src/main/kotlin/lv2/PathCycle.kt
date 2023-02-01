package lv2

class PathCycle {
    fun start() {
        val testCase = arrayOf(
            arrayOf("SL","LR"),
            arrayOf("S"),
            arrayOf("R","R"),
        )

        for (case in testCase) {
            solution(case).forEach { print(it) }
            println()
        }
    }

    fun solution(grid: Array<String>): IntArray {
        val sero = grid.size
        val garo = grid[0].length
        val max = garo * sero * 4
        val checkMap = BooleanArray( max ) { false }
        return checkMap.filter { !it }
            .mapIndexed { index, _ ->
                var c = index
                var length = 0
                while (!checkMap[c]) {
                    length += 1
                    val direction = c % 4
                    val j = (c / 4) % garo
                    val i = (c / 4) / garo
                    checkMap[c] = true
                    if (
                        grid[i][j] == 'S' && direction == 0 ||
                        grid[i][j] == 'R' && direction == 3 ||
                        grid[i][j] == 'L' && direction == 1
                    ) {
                        // \/
                        c = if (i == sero - 1) {
                            j * 4
                        } else {
                            ((i+1) * garo + j) * 4
                        }
                    } else if (
                        grid[i][j] == 'S' && direction == 1 ||
                        grid[i][j] == 'R' && direction == 0 ||
                        grid[i][j] == 'L' && direction == 2
                    ) {
                        // <-
                        c = if (j == 0) {
                            (i * garo + garo - 1) * 4 + 1
                        } else {
                            (i * garo + j - 1) * 4 + 1
                        }
                    } else if (
                        grid[i][j] == 'S' && direction == 2 ||
                        grid[i][j] == 'R' && direction == 1 ||
                        grid[i][j] == 'L' && direction == 3
                    ) {
                        // ^
                        c = if (i == 0) {
                            ((sero - 1) * garo + j) * 4 + 2
                        } else {
                            ((i - 1) * garo + j) * 4 + 2
                        }
                    } else {
                        // ->
                        c = if (j == garo - 1) {
                            i * garo * 4 + 3
                        } else {
                            (i * garo + j + 1) * 4 + 3
                        }
                    }
                }
                length
            }
            .filter { it != 0 }
            .toIntArray()
            .sortedArray()
    }
}
