//   0 1 2
// 0 F T F
// 1 F F F
// 2 F F F

class Solution {
    private fun countForward(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 1

        for (v in 0 until graph[u].size) {
            if (!graph[u][v] || isVisited[v]) continue
            isVisited[v] = true
            count += countForward(v, graph, isVisited)
        }
        return count
    }

    private fun countBackward(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 1

        for (v in 0 until graph.size) {
            if (!graph[v][u] || isVisited[v]) continue
            isVisited[v] = true
            count += countBackward(v, graph, isVisited)
        }
        return count
    }

    fun solution(n: Int, results: Array<IntArray>): Int {
        val graph = Array(n) { BooleanArray(n) }

        for (edge in results) {
            val u = edge[0] - 1
            val v = edge[1] - 1
            graph[u][v] = true
        }

        var count = 0
        for (u in 0 until n) {
            val wins = countForward(u, graph, BooleanArray(n)) - 1
            val loses = countBackward(u, graph, BooleanArray(n)) - 1
            if (wins + loses + 1 == n) {
                count++
            }
        }
        return count
    }
}