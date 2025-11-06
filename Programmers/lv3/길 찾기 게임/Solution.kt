// [길 찾기 게임]

// nodeinfo 데이터를 y 좌표 기준으로 내림차순 정렬

// Node Class (x, y, root, left, right)

// 정렬 결과를 노드로 재구성
// -> buildTree(), insert()

// 전위, 후위 탐색 결과 리턴

class Solution {
    private class Node(val x: Int, val y: Int, val num: Int, var left: Node?, var right: Node?)

    private fun insert(root: Node, child: Node) {
        if (child.x < root.x) {
            if (root.left == null) root.left = child
            else insert(root.left!!, child)
        } else {
            if (root.right == null) root.right = child
            else insert(root.right!!, child)
        }
    }

    private fun buildTree(nodes: List<Node>): Node {
        val root = nodes[0]
        for (i in 1 until nodes.size) {
            insert(root, nodes[i])
        }
        return root
    }

    private fun pre(root: Node, order: MutableList<Int>) {
        order.add(root.num)
        root.left?.let { pre(it, order) }
        root.right?.let { pre(it, order) }
    }

    private fun post(root: Node, order: MutableList<Int>) {
        root.left?.let { post(it, order) }
        root.right?.let { post(it, order) }
        order.add(root.num)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeinfo.mapIndexed { index, info ->
            Node(info[0], info[1], index + 1, null, null)
        }.sortedWith(compareBy { -it.y })

        val root = buildTree(nodes)

        val preOrder = mutableListOf<Int>()
        pre(root, preOrder)
        val postOrder = mutableListOf<Int>()
        post(root, postOrder)

        return arrayOf(preOrder.toIntArray(), postOrder.toIntArray())
    }
}