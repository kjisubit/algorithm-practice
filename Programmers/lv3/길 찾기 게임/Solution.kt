// [길 찾기 게임]

// 입력값으로 이진 트리 구성
// 노드는 클래스로 표현 (value, x, y, left?, right?)
// 전위, 후위 탐색 결과 리턴

class Solution {
    class Node(
        val value: Int,
        val x: Int,
        val y: Int,
        var left: Node?,
        var right: Node?
    )

    private fun insert(root: Node, child: Node) {
        if (root.x > child.x) {
            if (root.left == null) root.left = child
            else insert(root.left!!, child)
        } else {
            if (root.right == null) root.right = child
            else insert(root.right!!, child)
        }
    }

    private fun buildTree(nodes: Array<Node>): Node {
        val root = nodes[0]
        for (i in 1 until nodes.size) {
            insert(root, nodes[i])
        }
        return root
    }

    private fun pre(node: Node?, order: MutableList<Int>) {
        if (node == null) return
        order.add(node.value)
        pre(node.left, order)
        pre(node.right, order)
    }

    private fun post(node: Node?, order: MutableList<Int>) {
        if (node == null) return
        post(node.left, order)
        post(node.right, order)
        order.add(node.value)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeinfo.mapIndexed { i, _ ->
            Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null)
        }.sortedWith(compareBy { -it.y }).toTypedArray()

        val root = buildTree(nodes)
        val preOrder = mutableListOf<Int>()
        pre(root, preOrder)
        val postOrder = mutableListOf<Int>()
        post(root, postOrder)

        return arrayOf(preOrder.toIntArray(), postOrder.toIntArray())
    }
}