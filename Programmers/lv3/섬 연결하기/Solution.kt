// [섬 연결하기]

// 가중치가 최소값인 신장 트리 구성 -> 크루스칼 알고리즘

class Solution {
    private class Node(
        private var parent: Node? = null,
        private var depth: Int = 1
    ) {
        fun isConnected(node: Node): Boolean {
            return getRoot() == node.getRoot()
        }

        fun getRoot(): Node {
            if (parent == null) return this
            return parent!!.getRoot()
        }

        fun merge(node: Node) {
            if (isConnected(node)) return

            val root = getRoot()
            val nodeRoot = node.getRoot()

            if (root.depth > nodeRoot.depth) {
                nodeRoot.parent = root
            } else if (root.depth < nodeRoot.depth) {
                root.parent = nodeRoot
            } else {
                nodeRoot.parent = root
                root.depth++
            }
        }
    }

    private class Edge(val u: Int, val v: Int, val cost: Int)

    fun solution(n: Int, costs: Array<IntArray>): Int {
        val nodes = Array(n) { Node() }

        val edges = costs.map { intArray ->
            Edge(intArray[0], intArray[1], intArray[2])
        }.sortedWith(compareBy { it.cost })

        var cost = 0

        for (edge in edges) {
            val node1 = nodes[edge.u]
            val node2 = nodes[edge.v]

            if (node1.isConnected(node2)) continue

            node1.merge(node2)
            cost += edge.cost
        }

        return cost
    }
}