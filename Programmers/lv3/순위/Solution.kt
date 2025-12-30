// [순위]

// 인접행렬 생성 (방향 그래프)
// 메서드1: countWin 카운트
// 메서드2: countLose 카운트

class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        var graph = Array(n) { BooleanArray(n) }
        for (r in results) {
            val u = r[0] - 1
            val v = r[1] - 1
            graph[u][v] = true
        }

        var total = 0
        for (u in 0 until n) {
            val win = countWin(u, graph, BooleanArray(n))
            val lose = countLoss(u, graph, BooleanArray(n))
            if (win + lose + 1 == n) total++
        }

        return total
    }

    private fun countWin(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (v in 0 until graph[u].size) {
            if (!graph[u][v] || isVisited[v]) continue

            if (graph[u][v]) {
                count++
                isVisited[v] = true
                count += countWin(v, graph, isVisited)
            }
        }
        return count
    }

    private fun countLoss(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (v in 0 until graph[u].size) {
            if (!graph[v][u] || isVisited[v]) continue

            if (graph[v][u]) {
                count++
                isVisited[v] = true
                count += countLoss(v, graph, isVisited)
            }
        }
        return count
    }
}