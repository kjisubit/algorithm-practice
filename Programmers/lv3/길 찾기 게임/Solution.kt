// [길 찾기 게임]

// Node 클래스 생성
// nodeinfo를 Node 어레이로 변경
// Node 어레이를 y축 기준으로 내림 차순
// 전위순회
// 후위순회

class Solution {
    private class Node(
        val num: Int,
        val x: Int,
        val y: Int,
        var left: Node?,
        var right: Node?,
    )

    private fun genTree(nodeArray: List<Node>): Node {
        val root = nodeArray[0]
        for (i in nodeArray.indices) {
            if (i == 0) continue
            connectNode(root, nodeArray[i])
        }
        return root
    }

    private fun connectNode(parent: Node, child: Node) {
        if (child.x < parent.x) {
            parent.left?.let {
                connectNode(it, child)
            } ?: run {
                parent.left = child
            }
        } else {
            parent.right?.let {
                connectNode(it, child)
            } ?: run {
                parent.right = child
            }
        }
    }

    private fun preOrder(tree: Node, order: MutableList<Int>) {
        order.add(tree.num)
        if (tree.left != null) preOrder(tree.left!!, order)
        if (tree.right != null) preOrder(tree.right!!, order)
    }

    private fun postOrder(tree: Node, order: MutableList<Int>) {
        if (tree.left != null) postOrder(tree.left!!, order)
        if (tree.right != null) postOrder(tree.right!!, order)
        order.add(tree.num)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodeList = nodeinfo.mapIndexed { i, node ->
            Node(i + 1, node[0], node[1], null, null)
        }.sortedWith(compareBy { it.y * -1 })

        val tree = genTree(nodeList)

        val preOrderList = mutableListOf<Int>()
        preOrder(tree, preOrderList)

        val postOrderList = mutableListOf<Int>()
        postOrder(tree, postOrderList)

        return arrayOf(preOrderList.toIntArray(), postOrderList.toIntArray())
    }
}