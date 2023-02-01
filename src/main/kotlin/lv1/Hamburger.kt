package lv1

class Hamburger {
    fun start() {
        val testCase = arrayOf(
            intArrayOf(2, 1, 1, 2, 3, 1, 2, 3, 1),
            intArrayOf(1, 3, 2, 1, 2, 1, 3, 1, 2),
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private fun solution(ingredient: IntArray): Int {
        var answer = 0
        var burgers = mutableListOf(0)
        for (i: Int in ingredient.indices) {
            when(ingredient[i]) {
                1 -> {
                    if (burgers.last() == 0) {
                        burgers[burgers.lastIndex] = 1
                    } else if (burgers.last() == 1) {
                        burgers.add(1)
                    } else if (burgers.last() == 2) {
                        burgers.add(1)
                    } else {
                        if (burgers.size == 1) {
                            burgers = mutableListOf(0)
                        } else {
                            burgers.removeLast()
                        }
                        answer++
                    }
                }
                2 -> {
                    if (burgers.last() == 1) {
                        burgers[burgers.lastIndex] = 2
                    } else {
                        burgers = mutableListOf(0)
                    }
                }
                else -> {
                    if (burgers.last() == 2) {
                        burgers[burgers.lastIndex] = 3
                    } else {
                        burgers = mutableListOf(0)
                    }
                }
            }
        }
        return answer
    }
}
