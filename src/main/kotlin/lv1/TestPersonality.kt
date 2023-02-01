package lv1

class TestPersonality {
    fun start() {
        val surveyCase = arrayOf(
            arrayOf("AN", "CF", "MJ", "RT", "NA"),
            arrayOf("TR", "RT", "TR")
        )

        val choiceCase = arrayOf(
            intArrayOf(5, 3, 2, 7, 5),
            intArrayOf(7, 1 ,3)
        )

        for (i in surveyCase.indices) {
            println(solution(surveyCase[i], choiceCase[i]))
        }
    }

    private fun solution(survey: Array<String>, choices: IntArray): String {
        val score = IntArray(8) { 0 }
        val data = arrayOf("R", "T", "C", "F", "J", "M", "A", "N")
        for (i in choices.indices) {
            if (choices[i] < 4) {
                score[translate(survey[i][0])] += 4 - choices[i]
            } else if (choices[i] > 4) {
                score[translate(survey[i][1])] += choices[i] - 4
            }
        }
        var answer = ""
        for (i in 0 until 4) {
            answer += if (score[i*2] < score[i*2+1]) {
                data[i*2+1]
            } else {
                data[i*2]
            }
        }
        println()
        return answer
    }

    private fun translate(source: Char): Int {
        return when (source) {
            'R' -> 0
            'T' -> 1
            'C' -> 2
            'F' -> 3
            'J' -> 4
            'M' -> 5
            'A' -> 6
            else -> 7
        }
    }
}
