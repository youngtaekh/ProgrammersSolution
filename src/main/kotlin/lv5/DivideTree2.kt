package lv5

class DivideTree2 {
    fun solve(k: Int, num: IntArray, links: Array<IntArray>): Int {
        val head = findHead(num, links)
        println("head is $head")
        val source = mutableListOf<Int>()
        for (i in 0 until num.size-1) {
            source.add(i)
        }
        val answer = mutableListOf<Int>()
        combination(answer, num.size-1, Array(num.size-1) { false }, 0, k-1, head, num, links)
        println("answer size ${answer.size}")
        return answer.last()
    }

    private fun combination(
        answer: MutableList<Int>,
        size: Int,
        check: Array<Boolean>,
        start: Int,
        target: Int,
        head: Int,
        num: IntArray,
        links: Array<IntArray>
    ) {
        if(target == 0) {
            val headList = mutableListOf<Int>()
            for (idx in 0 until size) {
                if (check[idx]) {
                    if (idx >= head) {
                        headList.add(idx + 1)
                    } else {
                        headList.add(idx)
                    }
                }
            }
            var max = getSum(head, headList, num, links)
            for (h in headList) {
                val sum = getSum(h, headList, num, links)
                if (max < sum) {
                    max = sum
                }
            }
            if (answer.size == 0 || answer[answer.size-1] > max) {
                answer.add(max)
            }
        } else {
            for(i in start until size) {
                check[i] = true
                combination(answer, size, check, i + 1, target - 1, head, num, links)
                check[i] = false
            }
        }
    }

    private fun findHead(num: IntArray, links: Array<IntArray>): Int {
        val headArray = BooleanArray(num.size) { true }
        links.map { it ->
            it.filter { it != -1 }
                .map { headArray[it] = false }
        }
        for ((i, flag) in headArray.withIndex()) {
            if (flag) {
                return i
            }
        }
        return -1
    }

    private fun getSum(head: Int, heads: List<Int>?, num: IntArray, links: Array<IntArray>): Int {
        var current = head
        var sum = 0
        val stack = mutableListOf(-1)
        while (current != -1) {
            sum += num[current]
            if (!hasNext(links[current][0], heads) && !hasNext(links[current][1], heads)) {
                current = stack[stack.size-1]
                stack.removeAt(stack.size-1)
            } else if (hasNext(links[current][0], heads) && !hasNext(links[current][1], heads)) {
                current = links[current][0]
            } else if (!hasNext(links[current][0], heads) && hasNext(links[current][1], heads)) {
                current = links[current][1]
            } else {
                stack.add(links[current][1])
                current = links[current][0]
            }
        }
        return sum
    }

    private fun hasNext(child: Int, heads: List<Int>?): Boolean {
        if (child == -1) {
            return false
        }
        if (heads != null) {
            for (head in heads) {
                if (child == head) {
                    return false
                }
            }
        }
        return true
    }

    fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el ): List<List<T>> {
        return if(sub.isEmpty()) listOf(fin)
        else sub.flatMap { permutation(el, fin + it, sub - it) }
    }

    fun <T> combination(answer: MutableList<List<T>>, source: List<T>, check: Array<Boolean>, start: Int, target: Int) {
        if(target == 0) {
            answer.addAll( listOf(source.filterIndexed { index, t -> check[index] }) )
        } else {
            for(i in start until source.size) {
                check[i] = true
                combination(answer, source, check, i + 1, target - 1)
                check[i] = false
            }
        }
    }
}
