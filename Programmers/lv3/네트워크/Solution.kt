// [네트워크]

// 하나의 노드에 대한 dfs 수행할 때마다 count++
// 이미 방문한 노드의 경우 skip

class Solution {
    private fun visitAll(computer: Int, computers: Array<IntArray>, isVisited: BooleanArray) {
        val ad = ArrayDeque<Int>()
        ad.addLast(computer)

        while (ad.isNotEmpty()) {
            val c = ad.removeLast()

            if (isVisited[c]) continue
            isVisited[c] = true

            for (i in computers[c].indices) {
                if (computers[c][i] == 0) continue
                ad.addLast(i)
            }
        }
    }

    fun solution(n: Int, computers: Array<IntArray>): Int {
        var count = 0
        val isVisited = BooleanArray(n)
        for (computer in computers.indices) {
            if (isVisited[computer]) continue
            visitAll(computer, computers, isVisited)
            count++
        }

        return count
    }
}