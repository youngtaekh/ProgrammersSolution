package lv5

class Hamiltonian2 {
    fun solution(t: Array<IntArray>): Int {
        val relation = Array(t.size + 1) { mutableListOf<Int>() }
        val depthMap = mutableMapOf<Pair<Int, Int>, Int>()
        for (line in t) {
            relation[line[0]].add(line[1])
            relation[line[1]].add(line[0])
        }
        var answer = 0
        var log = ""
        for (i in relation.indices) {
            if (relation[i].size == 1) {
                val mainChain = mutableListOf<Int>()
                val max = fullSearch(relation, mainChain, depthMap, i)
                if (answer < max) {
                    answer = max
                    log = "$max - $mainChain"
                }
            }
        }
        println("max $log")
        return answer
    }

    private fun fullSearch(
        relation: Array<MutableList<Int>>,
        storeChain: MutableList<Int>,
        depthMap: MutableMap<Pair<Int, Int>, Int>,
        start: Int
    ): Int {
        val mainChain = mutableListOf<Int>()
        var maxChain = mutableListOf<Int>()
        val stack = mutableListOf(start)
        var max = 0
        while (stack.isNotEmpty()) {
            mainChain.add(stack.removeLast())
            if (relation[mainChain.last()].size == 1 && mainChain.size != 1) {
                val depth = findDepth(relation, depthMap, mainChain)
                if (max < depth + mainChain.size) {
                    max = depth + mainChain.size
                    maxChain = mutableListOf()
                    maxChain.addAll(mainChain)
                }
                var deleted = true
                while (deleted && mainChain.isNotEmpty()) {
                    for (check in relation[mainChain.last()]) {
                        if (stack.isNotEmpty() && stack.last() == check) {
                            deleted = false
                            break
                        }
                    }
                    if (deleted) {
                        mainChain.removeLast()
                    }
                }
            } else {
                for (next in relation[mainChain.last()]) {
                    if (mainChain.size == 1 || mainChain[mainChain.size - 2] != next) {
                        stack.add(next)
                    }
                }
            }
        }
        storeChain.addAll(maxChain)
        return max
    }

    private fun findDepth(
        relation: Array<MutableList<Int>>,
        depthMap: MutableMap<Pair<Int, Int>, Int>,
        mainChain: MutableList<Int>
    ): Int {
        var sum = 0
        var max = 0
        var depth = 0
        for (i in 1 until mainChain.size-1) {
            for (next in relation[mainChain[i]]) {
                if (next != mainChain[i-1] && next != mainChain[i+1]) {
                    if (depthMap.containsKey(mainChain[i] to next)) {
                        val value = depthMap[mainChain[i] to next]!!
                        if (value > max) {
                            max = value
                        }
                    } else {
                        val subChain = mutableListOf<Int>()
                        val subStack = mutableListOf(next)
                        while (subStack.isNotEmpty()) {
                            subChain.add(subStack.removeLast())
                            if (relation[subChain.last()].size == 1 && subChain.size != 1) {
                                if (depth < subChain.size) {
                                    depth = subChain.size
                                }
                                var deleted = true
                                while (deleted && subChain.isNotEmpty()) {
                                    for (check in relation[subChain.last()]) {
                                        if (subStack.isNotEmpty() && subStack.last() == check) {
                                            deleted = false
                                            break
                                        }
                                    }
                                    if (deleted) {
                                        subChain.removeLast()
                                    }
                                }
                            } else {
                                for (deeper in relation[subChain.last()]) {
                                    if ((subChain.size == 1 || subChain[subChain.size - 2] != deeper) &&
                                        deeper != mainChain[i]) {
                                        subStack.add(deeper)
                                    }
                                }
                            }
                        }
                        if (depth == 0) {
                            depth = subChain.size
                        }
                        if (max < depth) {
                            max = depth
                        }
                        depthMap[mainChain[i] to next] = depth
                        depth = 0
                    }
                }
            }
            sum += max
            max = 0
        }
        return sum
    }
}
