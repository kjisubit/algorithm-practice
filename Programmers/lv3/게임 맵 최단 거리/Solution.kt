// [게임 맵 최단거리]

// 최단 거리 찾기 문제 -> BFS -> 큐 사용
// 탐색 가능한 가장 가까운 상태를 순차적으로 큐에 저장

// 좌표 이동 구현 -> dx, dy

// 좌표 이동 시 범위 체크, 과거 방문 이력 체크, 벽 존재 여부 체크

class Solution {
    private class State(val x: Int, val y: Int, val steps: Int)

    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(maps: Array<IntArray>): Int {
        val q = ArrayDeque<State>()
        val isVisited = Array(maps.size) { BooleanArray(maps[0].size) }

        q.addLast(State(0, 0, 1))
        isVisited[0][0] = true

        while (q.isNotEmpty()) {
            val state = q.removeFirst()

            if (state.y == maps.size - 1 && state.x == maps[0].size - 1) return state.steps

            for (d in 0..3) {
                val ny = state.y + dy[d]
                val nx = state.x + dx[d]

                if (ny < 0 || ny >= maps.size || nx < 0 || nx >= maps[0].size) continue

                if (isVisited[ny][nx]) continue

                if (maps[ny][nx] == 0) continue

                q.addLast(State(nx, ny, state.steps + 1))
                isVisited[ny][nx] = true
            }
        }

        return -1
    }
}