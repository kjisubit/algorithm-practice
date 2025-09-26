// [섬 연결하기]

// 주어진 입력으로 최소 신장 트리 구성하도록
// 크루스칼 알고리즘 필요

class Solution053 {
    private class Node(var depth: Int = 1, var parent: Node? = null) {
        fun isConnected(o: Node): Boolean {
            return root() == o.root()
        }

        fun merge(o: Node) {
            if (isConnected(o)) return

            val root1 = root()
            val root2 = o.root()

            if (root1.depth > root2.depth) {
                root2.parent = root1
            } else if (root1.depth < root2.depth) {
                root1.parent = root2
            } else {
                root2.parent = root1;
                root1.depth += 1
            }
        }

        private fun root(): Node {
            if (parent == null) return this
            return parent!!.root()
        }
    }

    private class Edge(val u: Int, val v: Int, val cost: Int)

    fun solution(n: Int, costs: Array<IntArray>): Int {
        val edges = costs.map { Edge(it[0], it[1], it[2]) }
            .sortedWith(compareBy { it.cost }).toTypedArray()

        val nodes = Array(n) {
            Node()
        }

        var totalCost = 0
        for (edge in edges) {
            val node1 = nodes[edge.u]
            val node2 = nodes[edge.v]

            if (node1.isConnected(node2)) continue
            node1.merge(node2)
            totalCost += edge.cost
        }

        return totalCost
    }
}