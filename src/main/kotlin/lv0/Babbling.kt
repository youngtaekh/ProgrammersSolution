package lv0

class Babbling {
    fun start() {
        val testCase = arrayOf(
            arrayOf("aya", "yee", "u", "maa", "wyeoo"),
            arrayOf("ayaye", "uuuma", "ye", "yemawoo", "ayaa"),
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private fun solution(babbling: Array<String>): Int {
        val array = arrayOf("aya", "ye", "woo", "ma")
        var answer = 0
        for (value in babbling) {
            var s = value
            var l = s.length
            while (l > 1) {
                if (array[0] == s || array[1] == s || array[2] == s || array[3] == s) {
                    answer++
                    break
                }
                s = if (s.startsWith(array[0]) || s.startsWith(array[2])) {
                    s.substring(3)
                } else if (s.startsWith(array[1]) || s.startsWith(array[3])) {
                    s.substring(2)
                } else {
                    break
                }
                l = s.length
            }
        }
        return answer
    }
}
