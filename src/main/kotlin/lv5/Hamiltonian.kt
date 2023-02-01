package lv5

import kotlin.random.Random

class Hamiltonian {
    fun start() {
        val testCase1 = Array(19999) { IntArray(2) { 0 } }
        for (i in testCase1.indices) {
            testCase1[i] = intArrayOf(i, i + 1)
        }
        val testCase2 = Array(19999) { IntArray(2) { 0 } }
        for (i in testCase2.indices) {
            testCase2[i] = intArrayOf(0, i + 1)
        }
        val testCase3 = Array(19999) { IntArray(2) { 0 } }
        for (i in testCase3.indices) {
            testCase3[i] = intArrayOf(i / 3, i + 1)
        }
        val size = 150
        val testCase4 = Array(size-1) { IntArray(2) { 0 } }
        for (i in 0 until size-1) {
            val t = Random.nextInt(i + 1)
            testCase4[i] = intArrayOf(t, i + 1)
        }

        val testCase = arrayOf(
//            testCase1,
//            testCase2,
//            testCase3,
            testCase4,
//            arrayOf(
//                intArrayOf(29, 10), intArrayOf(18, 28), intArrayOf(11, 7),intArrayOf(2, 1),
//                intArrayOf(2, 3), intArrayOf(26, 8), intArrayOf(8, 0), intArrayOf(15, 0),
//                intArrayOf(0, 4), intArrayOf(1, 0), intArrayOf(8, 23), intArrayOf(16, 8),
//                intArrayOf(8, 9), intArrayOf(2, 12), intArrayOf(13, 5), intArrayOf(27, 6),
//                intArrayOf(1, 6), intArrayOf(2, 17), intArrayOf(5, 21), intArrayOf(22, 21),
//                intArrayOf(2, 7), intArrayOf(18, 13), intArrayOf(5, 14), intArrayOf(19, 5),
//                intArrayOf(5, 3), intArrayOf(7, 10), intArrayOf(10, 20), intArrayOf(20, 24),
//                intArrayOf(24, 25)
//            ),
//            arrayOf(
//                intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(2, 4),
//                intArrayOf(0, 5), intArrayOf(3, 6), intArrayOf(3, 7), intArrayOf(7, 8),
//                intArrayOf(6, 9), intArrayOf(8, 10), intArrayOf(9, 11), intArrayOf(10, 12),
//                intArrayOf(12, 13), intArrayOf(6, 14), intArrayOf(0, 15), intArrayOf(11, 16),
//                intArrayOf(15, 17), intArrayOf(8, 18), intArrayOf(9, 19), intArrayOf(12, 20),
//                intArrayOf(19, 21), intArrayOf(13, 22), intArrayOf(20, 23), intArrayOf(2, 24),
//                intArrayOf(10, 25), intArrayOf(17, 26), intArrayOf(26, 27), intArrayOf(17, 28),
//                intArrayOf(4, 29), intArrayOf(5, 30), intArrayOf(29, 31), intArrayOf(27, 32),
//                intArrayOf(0, 33), intArrayOf(24, 34), intArrayOf(23, 35), intArrayOf(32, 36),
//                intArrayOf(10, 37), intArrayOf(37, 38), intArrayOf(29, 39), intArrayOf(14, 40),
//                intArrayOf(18, 41), intArrayOf(34, 42), intArrayOf(22, 43), intArrayOf(2, 44),
//                intArrayOf(41, 45), intArrayOf(14, 46), intArrayOf(39, 47), intArrayOf(11, 48),
//                intArrayOf(9, 49), intArrayOf(47, 50), intArrayOf(32, 51), intArrayOf(11, 52),
//                intArrayOf(27, 53), intArrayOf(18, 54), intArrayOf(51, 55), intArrayOf(23, 56),
//                intArrayOf(28, 57), intArrayOf(46, 58), intArrayOf(43, 59), intArrayOf(30, 60),
//                intArrayOf(49, 61), intArrayOf(34, 62), intArrayOf(11, 63), intArrayOf(52, 64),
//                intArrayOf(64, 65), intArrayOf(56, 66), intArrayOf(11, 67), intArrayOf(49, 68),
//                intArrayOf(63, 69), intArrayOf(13, 70), intArrayOf(14, 71), intArrayOf(69, 72),
//                intArrayOf(44, 73), intArrayOf(47, 74), intArrayOf(73, 75), intArrayOf(27, 76),
//                intArrayOf(31, 77), intArrayOf(47, 78), intArrayOf(26, 79), intArrayOf(28, 80),
//                intArrayOf(56, 81), intArrayOf(77, 82), intArrayOf(71, 83), intArrayOf(48, 84),
//                intArrayOf(67, 85), intArrayOf(2, 86), intArrayOf(84, 87), intArrayOf(8, 88),
//                intArrayOf(58, 89), intArrayOf(35, 90), intArrayOf(27, 91), intArrayOf(24, 92),
//                intArrayOf(62, 93), intArrayOf(21, 94), intArrayOf(80, 95), intArrayOf(1, 96),
//                intArrayOf(94, 97), intArrayOf(27, 98), intArrayOf(48, 99), intArrayOf(60, 100),
//                intArrayOf(10, 101), intArrayOf(94, 102), intArrayOf(55, 103), intArrayOf(33, 104),
//                intArrayOf(100, 105), intArrayOf(96, 106), intArrayOf(100, 107), intArrayOf(94, 108),
//                intArrayOf(1, 109), intArrayOf(101, 110), intArrayOf(106, 111), intArrayOf(100, 112),
//                intArrayOf(45, 113), intArrayOf(28, 114), intArrayOf(101, 115), intArrayOf(84, 116),
//                intArrayOf(55, 117), intArrayOf(50, 118), intArrayOf(24, 119), intArrayOf(113, 120),
//                intArrayOf(64, 121), intArrayOf(103, 122), intArrayOf(49, 123), intArrayOf(1, 124),
//                intArrayOf(33, 125), intArrayOf(108, 126), intArrayOf(3, 127), intArrayOf(107, 128),
//                intArrayOf(60, 129), intArrayOf(30, 130), intArrayOf(68, 131), intArrayOf(131, 132),
//                intArrayOf(58, 133), intArrayOf(54, 134), intArrayOf(91, 135), intArrayOf(39, 136),
//                intArrayOf(73, 137), intArrayOf(18, 138), intArrayOf(88, 139), intArrayOf(51, 140),
//                intArrayOf(100, 141), intArrayOf(122, 142), intArrayOf(88, 143), intArrayOf(39, 144),
//                intArrayOf(58, 145), intArrayOf(1, 146), intArrayOf(41, 147), intArrayOf(19, 148),
//                intArrayOf(20, 149),
//            ),
//            arrayOf(intArrayOf(5, 1), intArrayOf(2, 5), intArrayOf(3, 5), intArrayOf(3, 6), intArrayOf(2, 4), intArrayOf(4, 0)),
//            arrayOf(intArrayOf(2, 5), intArrayOf(2, 0), intArrayOf(3, 2), intArrayOf(4, 2), intArrayOf(2, 1))
        )

        for (case in testCase) {
            println()
        }
    }

    private fun solution(t: Array<IntArray>): Int {
        val node = Array(t.size + 1) { Node() }
        setNode(node, t)
        setTree(node)
        return findMax(node)
    }

    private fun setNode(node: Array<Node>, t: Array<IntArray>) {
        t.forEach {
            val left = it[0]
            val right = it[1]
            node[left].name = left
            node[right].name = right
            node[left].children.add(right)
            node[right].children.add(left)
        }
    }

    private fun setTree(node: Array<Node>) {
        val stack = mutableListOf(node[ROOT])
        var parent = ROOT_PARENT
        while (stack.isNotEmpty()) {
            val pointer = stack.last()
            if (pointer.parent == INIT) {
                pointer.parent = parent
                if (parent != ROOT_PARENT) {
                    val parentNode = node[parent]
                    if (parentNode.parent == ROOT_PARENT) {
                        //child of root
                        pointer.rootChild = pointer.name
                        pointer.distance = 1
                    } else {
                        pointer.rootChild = parentNode.rootChild
                        pointer.distance = parentNode.distance + 1
                    }
                }
            }

            //End of Tree
            if (pointer.children.size == 1 && pointer.parent != ROOT_PARENT) {
                stack.removeLast()
                continue
            }
            var done = true
            for (childIndex in pointer.children) {
                if (childIndex != parent && node[childIndex].parent == INIT) {
                    stack.add(node[childIndex])
                    done = false
                }
            }

            if (done) {
                //calculate
                calcSubTree(node, pointer)
                parent = pointer.parent
                stack.removeLast()
            } else {
                parent = pointer.name
            }
        }
    }

    private fun calcSubTree(node: Array<Node>, pointer: Node) {
        val size = 3
        val threeWay = Array(size) { -1 to 0 }
        val twoWay = Array(size) { -1 to 0 }
        val oneWay = Array(size) { -1 to 0 }

        for (childIndex in pointer.children) {
            if (childIndex != pointer.parent) {
                val child = node[childIndex]
                saveMax(threeWay, childIndex to child.two)
                saveMax(twoWay, childIndex to child.oneOne)
                saveMax(oneWay, childIndex to child.one)
            }
        }
        pointer.two = threeWay[0].second + 1

        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    if (twoWay[i].first != twoWay[j].first &&
                        twoWay[i].first != oneWay[k].first &&
                        twoWay[j].first != oneWay[k].first
                    ) {
                        if (pointer.twoOne <= twoWay[i].second + twoWay[j].second + oneWay[k].second) {
                            pointer.twoOne = twoWay[i].second + twoWay[j].second + oneWay[k].second + 1
                        }
                    }
                }
            }
        }

        if (pointer.two <= twoWay[0].second + twoWay[1].second) {
            pointer.two = twoWay[0].second + twoWay[1].second + 1
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (twoWay[i].first != oneWay[j].first &&
                    twoWay[i].second + oneWay[j].second >= pointer.oneOne) {
                    pointer.oneOne = twoWay[i].second + oneWay[j].second + 1
                }
                if (threeWay[i].first != oneWay[j].first &&
                    threeWay[i].second + oneWay[j].second >= pointer.twoOne) {
                    pointer.twoOne = threeWay[i].second + oneWay[j].second + 1
                }
            }
        }

        pointer.one = oneWay[0].second + 1
    }

    private fun findMax(node: Array<Node>): Int {
        val stack = mutableListOf(node[ROOT])
        var max = 0
        while (stack.isNotEmpty()) {
            val pointer = stack.removeLast()
            if (max < pointer.twoOne) {
                max = pointer.twoOne
            }
            if (pointer.name != ROOT) {
                for (childIndex in node[ROOT].children) {
                    if (childIndex != pointer.rootChild) {
                        if (max < pointer.two + pointer.distance + node[childIndex].one) {
                            max = pointer.two + pointer.distance + node[childIndex].one
                        }
                    }
                }
            }
            for (childIndex in pointer.children) {
                if (childIndex != pointer.parent) {
                    stack.add(node[childIndex])
                }
            }
        }
        return max
    }

    private fun saveMax(save: Array<Pair<Int, Int>>, value: Pair<Int, Int>) {
        for (i in save.indices) {
            if (save[i].second < value.second) {
                for (j in save.size-1 downTo i+1) {
                    save[j] = save[j-1]
                }
                save[i] = value
                break
            }
        }
    }

    companion object {
        private const val INIT = -2
        private const val ROOT_PARENT = -1
        private const val ROOT = 0
    }

    class Node {
        var parent = INIT
        var name = INIT
        //two sub tree, one depth
        var twoOne = 1
        //two sub tree
        var two = 1
        //one sub tree, one depth
        var oneOne = 1
        //one depth
        var one = 1
        var rootChild = INIT
        var distance = 0
        var children = mutableListOf<Int>()

        override fun toString(): String {
            val sb = java.lang.StringBuilder()
            sb.append("parent: $parent, ")
            sb.append("name: $name, ")
            sb.append("($twoOne, $two, $oneOne, $one), ")
            sb.append("root: $rootChild, ")
            sb.append("path: $distance, ")
            if (children.isNotEmpty()) {
                sb.append("children: $children")
            }

            return sb.toString()
        }
    }
}
