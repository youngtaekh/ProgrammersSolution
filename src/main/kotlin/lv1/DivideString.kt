package lv1

class DivideString {
    fun start() {
        val testCase = arrayOf(
            "banana",
            "abracadabra",
            "aaabbaccccabba"
        )

        for (case in testCase) {
            println(solution(case))
        }
    }

    private fun solution(s: String): Int {
        val a = s.fold("" to listOf<String>()) { (acc, list), c ->
            if (acc.isNotBlank() && (acc + c).groupBy { it }[acc[0]]?.size == (acc + c).filter { it != acc[0] }.length) "" to list + listOf(acc + c) else acc + c to list
        }.let { (x, list) -> list.size + if (x.isNotBlank()) 1 else 0 }
        println(a)

        var answer = 0
        var same = 0
        var different = 0
        var startChar = s[0]
        s.forEach {
            if (same == 0 && different == 0) {
                same = 1
                startChar = it
            } else  if (it == startChar) {
                same += 1
            } else {
                different += 1
            }
            if (same == different) {
                same = 0
                different = 0
                answer += 1
            }
        }
        if (same != different) {
            answer += 1
        }
        return answer
    }
}
