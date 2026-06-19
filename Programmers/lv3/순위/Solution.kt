// [순위]

// 1. 입력값으로 인접 행렬 만들기
// 결과 -> T, 그 외 -> F

// 2. 승리, 패배 카운트 하기
// u 순회 후 각 u에 대한 승리 및 패배 카운트
// 총 합이 n - 1인 케이스에 한해 카운트 가능

class Solution{
    fun solution(n: Int, results: Array<IntArray>): Int {
        val graph = Array<BooleanArray>(n) { BooleanArray(n) }
        results.forEach { result ->
            val u = result[0] - 1
            val v = result[1] - 1
            graph[u][v] = true
        }

        var answer = 0
        for (u in 0 until n) {
            val isVisited = BooleanArray(n)
            val winCount = countWin(u, graph, isVisited)
            val loseCount = countLose(u, graph, isVisited)
            if (winCount + loseCount == n - 1) answer++
        }
        return answer
    }

    private fun countWin(u: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (v in 0 until graph.size) {
            if (isVisited[v] || !graph[u][v]) continue

            if (graph[u][v]) {
                isVisited[v] = true
                count++
                count += countWin(v, graph, isVisited)
            }
        }
        return count
    }

    private fun countLose(v: Int, graph: Array<BooleanArray>, isVisited: BooleanArray): Int {
        var count = 0
        for (u in 0 until graph.size) {
            if (isVisited[u] || !graph[u][v]) continue

            if (graph[u][v]) {
                isVisited[u] = true
                count++
                count += countLose(u, graph, isVisited)
            }
        }
        return count
    }
}