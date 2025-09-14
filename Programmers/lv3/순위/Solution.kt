// [순위]

// 순위 파악이 가능한 선수는 파악 가능한 싸움 결과(승리 + 패배)의 총 합이 n - 1인 경우
// 인접 행렬을 탐색하여 승리, 패배의 수 파악
// 승리는 u -> v -> z 순으로 파악하고, 패배는 역순인 u -> t -> s으로 카운트

class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val graph = Array(n) { BooleanArray(n) }
        for (result in results) {
            val u = result[0] - 1
            val v = result[1] - 1
            graph[u][v] = true
        }

        var total = 0
        for (u in 0 until n) {
            val winCount = countWin(u, graph, BooleanArray(n))
            val loseCount = countLose(u, graph, BooleanArray(n))
            if (winCount + loseCount == n - 1) total++
        }
        return total
    }

    private fun countWin(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (v in 0 until graph[u].size) {
            if (!graph[u][v] || isVisited[v]) continue

            if (graph[u][v]) {
                count++
                count += countWin(v, graph, isVisited)
                isVisited[v] = true
            }
        }

        return count
    }

    private fun countLose(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (v in 0 until graph.size) {
            if (!graph[v][u] || isVisited[v]) continue

            if (graph[v][u]) {
                count++
                count += countLose(v, graph, isVisited)
                isVisited[v] = true
            }
        }

        return count
    }
}