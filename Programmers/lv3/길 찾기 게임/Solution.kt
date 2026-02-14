// [길 찾기 게임]

// 클래스 -> 노드
// -- Node Class (x, y, value, left, right)

// nodeinfo 노드 클래스 변환
// y 좌표 기준 오름차순 정렬

// 메서드 -> 트리 생성
// generateTree()

// 메서드 -> 재귀 삽입
// add()

// 메서드 -> 전위 순회
// pre()

// 메서드 -> 후위 순회
// post()

class Solution {
    private class Node(val x: Int, val y: Int, val value: Int, var left: Node?, var right: Node?)

    private fun generateTree(nodes: List<Node>): Node {
        val root = nodes[0]
        for (i in 1 until nodes.size) {
            add(root, nodes[i])
        }
        return root
    }

    private fun add(parent: Node, child: Node) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child
            else add(parent.left!!, child)
        } else {
            if (parent.right == null) parent.right = child
            else add(parent.right!!, child)
        }
    }

    private fun pre(tree: Node, order: MutableList<Int>) {
        order.add(tree.value)
        tree.left?.let {
            pre(it, order)
        }
        tree.right?.let {
            pre(it, order)
        }
    }

    private fun post(tree: Node, order: MutableList<Int>) {
        tree.left?.let {
            post(it, order)
        }
        tree.right?.let {
            post(it, order)
        }
        order.add(tree.value)
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeinfo.mapIndexed { i, intArray ->
            Node(intArray[0], intArray[1], i + 1, null, null)
        }.sortedWith(compareBy { -it.y })

        val tree = generateTree(nodes)

        val preOrder = mutableListOf<Int>()
        pre(tree, preOrder)
        val postOrder = mutableListOf<Int>()
        post(tree, postOrder)

        return arrayOf(preOrder.toIntArray(), postOrder.toIntArray())
    }
}