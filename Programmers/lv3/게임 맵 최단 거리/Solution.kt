// [게임 맵 최단거리]

// 최단 거리 찾기 문제 -> BFS
// 2차원 배열로 좌표 이동 구현 -> dx, dy
// 좌표 이동 시 방문 지점, 범위 체크, 벽 존재 여부 체크

class Solution {
    private class State(val x: Int, val y: Int, val step: Int)

    private val dx = arrayOf<Int>(0, 0, -1, 1)
    private val dy = arrayOf<Int>(-1, 1, 0, 0)

    fun solution(maps: Array<IntArray>): Int {
        val isVisited = Array(maps.size) { BooleanArray(maps[0].size) }

        val ad = ArrayDeque<State>()
        ad.addLast(State(0, 0, 1))
        isVisited[0][0] = true

        while (ad.isNotEmpty()) {
            val state = ad.removeFirst()

            if (state.y == maps.size - 1 && state.x == maps[state.y].size - 1) return state.step

            for (d in 0..3) {
                val ny = state.y + dy[d]
                val nx = state.x + dx[d]

                if (ny < 0 || ny >= maps.size || nx < 0 || nx >= maps[ny].size) continue

                if (isVisited[ny][nx]) continue

                if (maps[ny][nx] == 0) continue

                isVisited[ny][nx] = true

                ad.addLast(State(nx, ny, state.step + 1))
            }
        }

        return -1
    }
}