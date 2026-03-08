// [게임 맵 최단거리]

// 최단 거리 찾기 -> BFS

// 클래스
// 멤버: 좌표(x, y),

// 좌표 이동 문제 -> dx, dy

// 방문 검사 -> flag 어레이

class Solution {
    private class State(val x: Int, val y: Int, val count: Int)

    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(maps: Array<IntArray>): Int {
        val q = ArrayDeque<State>()
        val visits = Array(maps.size) {
            BooleanArray(maps[0].size)
        }

        q.addLast(State(0, 0, 1))
        visits[0][0] = true

        while (q.isNotEmpty()) {
            val state = q.removeFirst()

            if (state.y == maps.size - 1 && state.x == maps[0].size - 1) return state.count

            for (d in 0..3) {
                val ny = state.y + dy[d]
                val nx = state.x + dx[d]

                if (ny < 0 || nx < 0 || ny >= maps.size || nx >= maps[0].size) continue

                if (visits[ny][nx]) continue

                if (maps[ny][nx] == 0) continue

                val newState = State(nx, ny, state.count + 1)
                q.addLast(newState)
                visits[ny][nx] = true
            }
        }

        return -1
    }
}