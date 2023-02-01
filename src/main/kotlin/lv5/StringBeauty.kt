package lv5

class StringBeauty {
    fun start() {
        val testCase = arrayOf(
            "baby",
            "oo",
            "aabbaaaabbaa"
        )

        for (case in testCase) {
            println(solution(case))
            println(wrongSolution(case))
        }
    }

    private fun solution(s: String): Long {
        var len: Long = s.length.toLong() * (s.length + 1).toLong() * (s.length - 1).toLong() / 6L
        println("len: $len")
        val charSet = Array<MutableMap<Int, Int>>(26) { mutableMapOf() }
        val tArray = LongArray(26) { 0L }
        val sArray = LongArray(26) { 0L }
        var ch = s[0]
        var idx: Int
        var count = 1
        for (i in 1 until s.length) {
            if (ch != s[i]) {
                idx = ch.code - 97
                saveData(charSet[idx], tArray, sArray, ch, count)
                ch = s[i]
                count = 1
            } else {
                count += 1
            }
        }
        idx = ch.code - 97
        saveData(charSet[idx], tArray, sArray, ch, count)

        charSet.mapIndexed { index, mutableMap ->
            count = 1
            while (mutableMap.isNotEmpty()) {
                len -= sArray[index] * (sArray[index] - 1L) / 2L
//                println("${(index + 97).toChar()}: $mutableMap, ${tArray[index]}, ${sArray[index]}")
//                println(sArray[index] * (sArray[index] - 1L) / 2L)
//                println(len)
                sArray[index] -= tArray[index]
                if (mutableMap.containsKey(count)) {
                    tArray[index] -= mutableMap[count]!!.toLong()
                    mutableMap.remove(count)
                }
                count += 1
            }
        }
        return len
    }

    private fun saveData(
        map: MutableMap<Int, Int>,
        t: LongArray,
        s: LongArray,
        ch: Char,
        count: Int
    ) {
        val idx = ch.code - 97
        if (map.containsKey(count)) {
            val value = map[count]!! + 1
            map[count] = value
        } else {
            map[count] = 1
        }
        t[idx] += 1L
        s[idx] += count.toLong()
    }

    fun wrongSolution(s: String): Long {
        var answer = 0L
        for (i in s.indices) {
            var result = 0L
            for (j in i + 2 .. s.length) {
                val subString = s.substring(i, j)
                result = findBeauty(subString, result)
                if (result < 0) {
                    result = 0
                }
                answer += result
                if ((i * j + j) % 10000 == 0) {
                    println("($i, $j) $subString, $result")
                }
            }
        }
        return answer
    }

    private fun findBeauty(s: String, result: Long): Long {
//        if (s[0] != s.last()) {
//            return (s.length - 1).toLong()
//        } else {
//            if (s[1] != s.last()) {
//                return (s.length - 2).toLong()
//            }
//            return result
//        }
        var left = 0
        var right = s.length-1
        var count = 0
        while (right >= 0) {
            if (s[left] == s[right]) {
                if (count == 0 || count > 0 && s[left-1] == s[left]) {
                    count += 1
                    left += 1
                    right -= 1
                } else {
                    break
                }
            } else {
                break
            }
        }
        return (s.length - 1 - count).toLong()
    }
}
