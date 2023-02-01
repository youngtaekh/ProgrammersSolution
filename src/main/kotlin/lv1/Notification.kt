package lv1

import java.util.*
import kotlin.reflect.typeOf

class Notification {
    fun start() {
        val testCase1 = arrayOf(
            arrayOf("muzi", "frodo", "apeach", "neo"),
            arrayOf("con", "ryan")
        )
        val testCase2 = arrayOf(
            arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"),
            arrayOf("ryan con", "ryan con", "ryan con", "ryan con")
        )
        val testCase3 = intArrayOf(2, 3)

        for (i in testCase1.indices) {
            solution(testCase1[i], testCase2[i], testCase3[i]).forEach { print("$it ") }
            println()
            solution2(testCase1[i], testCase2[i], testCase3[i]).forEach { print("$it ") }
            println()
        }
    }

    private fun solution(id_list: Array<String>, reports: Array<String>, k: Int): IntArray {
        val a = reports.map { it.split(" ") }
        println("a = $a")
        val b = a.groupBy { it[1] }
        println("b = $b")
//        val c = b.asSequence()
//        println("c = $c")
        val d = b.map { it.value.distinct() }
        println("d = $d")
        val e = d.filter { it.size >= k }
        println("e = $e")
        val e1 = e.flatten()
        println("e1 = $e1")
        val f = e1.map { it[0] }
        println("f = $f")
        val g = f.groupingBy { it }
        println("g = $g")
        val h = g.eachCount()
        println("h = $h")
        return reports.map { it.split(" ") }
            .groupBy { it[1] }
//            .asSequence()
            .map { it.value.distinct() }
            .filter { it.size >= k }
            .flatten()
            .map { it[0] }
            .groupingBy { it }
            .eachCount()
            .run { id_list.map { getOrDefault(it, 0) }.toIntArray() }
    }

    private fun solution2(id_list: Array<String>, reports: Array<String>, k: Int): IntArray {
        val answer = IntArray(id_list.size) { 0 }
        val idMap = mutableMapOf<String, Int>()
        val relation = Array(id_list.size) { BooleanArray(id_list.size) { false } }
        val count = IntArray(id_list.size) { 0 }
        for (i in id_list.indices) {
            idMap[id_list[i]] = i
        }
        for (report in reports) {
            val tokenizer = StringTokenizer(report, " ")
            val a = idMap[tokenizer.nextToken()]!!
            val b = idMap[tokenizer.nextToken()]!!
            if (!relation[a][b]) {
                // increase notified count
                count[b] += 1
                // check relation
                relation[a][b] = true
            }
        }
        for (i in id_list.indices) {
            if (count[i] >= k) {
                for (j in id_list.indices) {
                    if (relation[j][i]) {
                        answer[j] += 1
                    }
                }
            }
        }
        return answer
    }
}
