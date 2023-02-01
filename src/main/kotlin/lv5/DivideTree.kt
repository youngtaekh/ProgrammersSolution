package lv5

import java.util.*

class DivideTree {
    fun start() {
        val k = intArrayOf(3, 3)
        val num = arrayOf(
            intArrayOf(12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1),
            intArrayOf(6, 9, 7, 5)
        )
        val links = arrayOf(
            arrayOf(
                intArrayOf(-1, -1),//0
                intArrayOf(-1, -1),//1
                intArrayOf(-1, -1),//2
                intArrayOf(-1, -1),//3
                intArrayOf(8, 5),//4
                intArrayOf(2, 10),//5
                intArrayOf(3, 0),//6
                intArrayOf(6, 1),//7
                intArrayOf(11, -1),//8
                intArrayOf(7, 4),//9
                intArrayOf(-1, -1),//10
                intArrayOf(-1, -1)//11
            ),
            arrayOf(
                intArrayOf(-1, -1),
                intArrayOf(-1, -1),
                intArrayOf(-1, 0),
                intArrayOf(2, 1)
            )
        )

        for (i in k.indices) {
            println(solution(k[i], num[i], links[i]))
        }
    }
    private var root = 0
    private var size = 0
    private lateinit var tree: Array<Node?>
    private lateinit var parent: IntArray

    fun solution(k: Int, num: IntArray, links: Array<IntArray>): Int {
        root = 0
        size = 0
        setTree(num, links)
        var low = 0
        var high = 1e9.toInt()
        for (n in num) {
            low = low.coerceAtLeast(n)
        }
        while (low <= high) {
            val mid = (low + high) / 2
            if (checkGroup(num, mid) <= k) {
                high = mid - 1
            } else {
                low = mid + 1
            }
            println("high: $high, low: $low, size: $size")
        }
        return high + 1
    }

    private fun checkGroup(num: IntArray, max: Int): Int {
        size = 0
        dfs(num, root, max)
        return size + 1
    }

    private fun dfs(num: IntArray, cur: Int, max: Int): Int {
        var lv = 0
        var rv = 0
        if (tree[cur]!!.left != -1) {
            lv = dfs(num, tree[cur]!!.left, max)
        }
        if (tree[cur]!!.right != -1) {
            rv = dfs(num, tree[cur]!!.right, max)
        }

        println("num[$cur]: ${num[cur]}, lv: $lv, rv: $rv, max: $max")
        if (num[cur] + lv + rv <= max) {
            return num[cur] + lv + rv
        }

        if (num[cur] + lv.coerceAtMost(rv) <= max) {
            size += 1
            println("cut 1")
            return num[cur] + lv.coerceAtMost(rv)
        }

        println("cut 2")
        size += 2
        return num[cur]
    }

    private fun setTree(num: IntArray, links: Array<IntArray>) {
        tree = arrayOfNulls(num.size)
        parent = IntArray(num.size) { -1 }
        for (i in links.indices) {
            tree[i] = Node(links[i][0], links[i][1])
            if (tree[i]!!.left != -1) parent[tree[i]!!.left] = i
            if (tree[i]!!.right != -1) parent[tree[i]!!.right] = i
        }
        for (i in parent.indices) {
            if (parent[i] == -1) {
                root = i
                break
            }
        }
    }

    internal class Node(var left: Int, var right: Int)
}
