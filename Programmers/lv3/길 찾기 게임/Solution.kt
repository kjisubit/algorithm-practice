class Solution {
    private class Node(val value: Int, val x: Int, val y: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    private fun insert(root: Node, node: Node) {
        if (node.x < root.x) {
            if (root.left == null) {
                root.left = node
            } else {
                insert(root.left!!, node)
            }
        } else {
            if (root.right == null) {
                root.right = node
            } else {
                insert(root.right!!, node)
            }
        }
    }

    private fun constructTree(nodes: Array<Node>): Node {
        val root = nodes[0]
        for (i in 1 until nodes.size) {
            insert(root, nodes[i])
        }

        return root
    }

    private fun pre(node: Node?, visits: MutableList<Int>) {
        if (node == null) return
        visits.add(node.value)
        pre(node.left, visits)
        pre(node.right, visits)
    }

    private fun post(node: Node?, visits: MutableList<Int>) {
        if (node == null) return
        post(node.left, visits)
        post(node.right, visits)
        visits.add(node.value)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = Array(nodeinfo.size) { i ->
            Node(i + 1, nodeinfo[i][0], nodeinfo[i][1])
        }
        nodes.sortWith { a, b -> b!!.y - a!!.y }

        val root = constructTree(nodes)

        val preOrder = mutableListOf<Int>()
        pre(root, preOrder)

        val postOrder = mutableListOf<Int>()
        post(root, postOrder)

        return arrayOf(preOrder.toIntArray(), postOrder.toIntArray())
    }
}