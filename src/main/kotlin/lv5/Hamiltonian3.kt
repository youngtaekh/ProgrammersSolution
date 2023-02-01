package lv5

class Hamiltonian3 {
    fun solution(t: Array<IntArray>): Int {
        val pInfo = Array(t.size + 1) { Tree() }
        val rootIndex = setPointInfo(pInfo, t)
        if (pInfo[rootIndex].children.size < 3) {
            return t.size + 1
        }
        searchTree(pInfo, rootIndex)
        val answer = findMax(pInfo, rootIndex)
//        pInfo
//            .filter { it.chain!!.sub != it.chain!!.main }
//            .map { println(it) }
        return answer
    }

    private fun setPointInfo(pInfo: Array<Tree>, t: Array<IntArray>): Int {
        var max = 0
        var maxIndex = -1
        for (line in t) {
            pInfo[line[0]].name = line[0]
            pInfo[line[1]].name = line[1]
            pInfo[line[0]].children.add(line[1])
            pInfo[line[1]].children.add(line[0])
            if (pInfo[line[0]].children.size > max) {
                max = pInfo[line[0]].children.size
                maxIndex = line[0]
            }
            if (pInfo[line[1]].children.size > max) {
                max = pInfo[line[1]].children.size
                maxIndex = line[1]
            }
        }
        return maxIndex
    }

    private fun searchTree(pInfo: Array<Tree>, root: Int) {
        val stack = mutableListOf(pInfo[root])
        while (stack.isNotEmpty()) {
            val p = stack.last()
            if (!p.hasChild()) {
                when (p.children.size) {
                    0 -> p.chain = ChainInfo(1, 1, 1)
                    1 -> p.chain = ChainInfo(
                        pInfo[p.children[0]].chain!!.twoMain + 1,
                        pInfo[p.children[0]].chain!!.main + 1,
                        pInfo[p.children[0]].chain!!.sub + 1)
                    else -> calcInfo(pInfo, p)
                }
                val last = stack.removeLast()
                last.checkedIndex = 0
            } else {
                while (p.hasChild()) {
                    val childIndex = p.getChild()
                    if (childIndex != p.parent) {
                        pInfo[childIndex].parent = p.name
                        if (p.name == root) {
                            pInfo[childIndex].rootChild = pInfo[childIndex].name
                        } else {
                            pInfo[childIndex].rootChild = p.rootChild
                        }
                        pInfo[childIndex].pathSize = p.pathSize + 1
                        stack.add(pInfo[childIndex])
                    } else {
                        p.children.remove(childIndex)
                        p.checkedIndex -= 1
                    }
                }
            }
        }
    }

    private fun calcInfo(pInfo: Array<Tree>, p: Tree) {
        val temp = mutableListOf<Int>()
        var main = 0
        var twoMainMax = 0
        val mainMax = Array(2) { -1 to 0 }
        val subMax = Array(2) { -1 to 0 }
        for (childIndex in p.children) {
            val chain = pInfo[childIndex].chain!!
            if (subMax[0].second < chain.sub) {
                subMax[1] = subMax[0]
                subMax[0] = childIndex to chain.sub
            } else if (subMax[1].second < chain.sub) {
                subMax[1] = childIndex to chain.sub
            }
            if (mainMax[0].second < chain.main) {
                mainMax[1] = mainMax[0]
                mainMax[0] = childIndex to chain.main
            } else if (mainMax[1].second < chain.main) {
                mainMax[1] = childIndex to chain.main
            }
            if (twoMainMax < chain.twoMain) {
                twoMainMax = chain.twoMain
            }
            if (chain.sub == chain.main) {
                p.setStrait(chain.sub)
            } else {
                temp.add(childIndex)
            }
        }
        p.children = temp
        for (i in 0 .. 1) {
            for (j in 0 .. 1) {
                if (mainMax[i].first != subMax[j].first) {
                    if (main <= mainMax[i].second + subMax[j].second) {
                        main = mainMax[i].second + subMax[j].second + 1
                    }
                }
            }
        }
        if (twoMainMax < mainMax[0].second + mainMax[1].second) {
            twoMainMax = mainMax[0].second + mainMax[1].second
        }
        p.chain = ChainInfo(twoMainMax + 1, main, subMax[0].second + 1)
    }

    private fun calcRootInfo(pInfo: Array<Tree>, root: Tree): Int {
        var main = 0
        val size = 3
        var main2Max = -1 to 0
        val mainMax = Array(size) { -1 to 0 }
        val subMax = Array(size) { -1 to 0 }
        for (childIndex in root.children) {
            val chain = pInfo[childIndex].chain!!
            if (main2Max.second < chain.twoMain) {
                main2Max = childIndex to chain.twoMain
            }
            for (i in 0 until size) {
                if (subMax[i].second < chain.sub) {
                    for (j in size-1 downTo i+1) subMax[j] = subMax[j-1]
                    subMax[i] = childIndex to chain.sub
                    break
                }
            }
            for (i in 0 until size) {
                if (mainMax[i].second < chain.main) {
                    for (j in size-1 downTo i+1) mainMax[j] = mainMax[j-1]
                    mainMax[i] = childIndex to chain.main
                    break
                }
            }
        }
        for (sIndex in root.strait.indices) {
            if (main2Max.second < root.strait[sIndex]) {
                main2Max = -1 to root.strait[sIndex]
            }
            for (i in 0 until size) {
                if (subMax[i].second < root.strait[sIndex]) {
                    for (j in size-1 downTo i+1) subMax[j] = subMax[j-1]
                    subMax[i] = (sIndex+1)*-1 to root.strait[sIndex]
                    break
                }
            }
            for (i in 0 until size) {
                if (mainMax[i].second < root.strait[sIndex]) {
                    for (j in size-1 downTo i+1) mainMax[j] = mainMax[j-1]
                    mainMax[i] = (sIndex+1)*-1 to root.strait[sIndex]
                    break
                }
            }
        }
        for (main1 in mainMax) {
            for (main2 in mainMax) {
                for (sub in subMax) {
                    if (main1.first != sub.first && main1.first != main2.first && main2.first != sub.first) {
                        if (main <= main1.second + main2.second + sub.second) {
                            main = main1.second + main2.second + sub.second + 1
                        }
                    }
                }
            }
        }
        for (sub in subMax) {
            if (main2Max.first != sub.first && main <= main2Max.second + sub.second) {
                main = main2Max.second + sub.second + 1
            }
        }
        return main
    }

    private fun findMax(pInfo: Array<Tree>, root: Int): Int {
        var max = 0
        var index = 0
        val stack = mutableListOf(pInfo[root])
        while (stack.isNotEmpty()) {
            val p = stack.last()
            if (!p.hasChild()) {
                if (p.name == root) {
                    val rootMax = calcRootInfo(pInfo, pInfo[root])
                    if (rootMax > max) {
                        println(pInfo[root])
                        println("$root $rootMax")
                        return rootMax
                    }
                    println(pInfo[index])
                    println("$index $max")
                    return max
                }
                val exceptIndex = p.rootChild
                var subMax = 0
                for (child in pInfo[root].children) {
                    if (child != exceptIndex && subMax < pInfo[child].chain!!.sub) {
                        subMax = pInfo[child].chain!!.sub
                    }
                }
                if (pInfo[root].strait.size > 0 && pInfo[root].strait[0] > subMax) {
                    subMax = pInfo[root].strait[0]
                }
                if (p.chain!!.twoMain + p.pathSize + subMax > max) {
                    index = p.name
                    max = p.chain!!.twoMain + p.pathSize + subMax
                }
                val rootMax = calcRootInfo(pInfo, p)
                if (rootMax > max) {
                    index = p.name
                    max = rootMax
                }
                stack.removeLast()
            } else {
                while (p.hasChild()) {
                    val childIndex = p.getChild()
                    stack.add(pInfo[childIndex])
                }
            }
        }
        return max
    }

    class Tree {
        var parent = -1
        var name = -1
        var chain: ChainInfo? = null
        var checkedIndex = 0
        var rootChild = -1
        var pathSize = 0
        val strait = mutableListOf<Int>()
        var children = mutableListOf<Int>()

        fun hasChild(): Boolean {
            return children.size != 0 && children.size > checkedIndex
        }

        fun getChild(): Int {
            return children[checkedIndex++]
        }

        fun setStrait(value: Int) {
            for (i in strait.indices) {
                if (i > 2) {
                    return
                }
                if (strait[i] <= value) {
                    strait.add(i, value)
                    if (strait.size > 3) {
                        strait.removeLast()
                    }
                    return
                }
            }
            strait.add(value)
        }

        override fun toString(): String {
            val sb = java.lang.StringBuilder()
            sb.append("parent: $parent, ")
            sb.append("name: $name, ")
            if (chain != null) {
                sb.append("$chain, ")
            }
            sb.append("root: $rootChild, ")
            sb.append("path: $pathSize, ")
            if (strait.isNotEmpty()) {
                sb.append("strait: $strait, ")
            }
            if (children.isNotEmpty()) {
                sb.append("children: $children")
            }

            return sb.toString()
        }
    }

    data class ChainInfo(val twoMain: Int, val main: Int, val sub: Int) {
        override fun toString(): String {
            return "Chain($twoMain, $main, $sub)"
        }
    }
}
