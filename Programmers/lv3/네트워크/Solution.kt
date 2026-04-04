// [네트워크]

// 완전 탐색 필요 -> dfs

// 프로세스
// -- 노드 방문 후 완전 탐색 완료될 때마다 count++

class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var count = 0
        val isVisited = BooleanArray(n)

        for (c in computers.indices) {
            if (isVisited[c]) continue
            visitNetwork(c, computers, isVisited)
            count++
        }

        return count
    }

    private fun visitNetwork(c: Int, computers: Array<IntArray>, isVisited: BooleanArray) {
        val ad = ArrayDeque<Int>()
        ad.addLast(c)

        while (ad.isNotEmpty()) {
            val top = ad.removeLast()

            if (isVisited[top]) continue
            isVisited[top] = true

            for (i in computers[top].indices) {
                if (computers[top][i] == 0) continue
                ad.addLast(i)
            }
        }
    }
}