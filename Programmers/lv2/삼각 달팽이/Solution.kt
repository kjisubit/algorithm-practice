// [삼각 달팽이]

// 좌표 이동 문제
// 하, 우, 상좌

// 방향 틀기 연속 2번 실패 시 종료

// 방향 틀기 조건
// 진행하고자 하는 지점에 숫자 존재
// 범위 초과

class Solution {
    private val dx = intArrayOf(0, 1, -1)
    private val dy = intArrayOf(1, 0, -1)

    fun solution(n: Int): IntArray {
        val square = Array<IntArray>(n) { IntArray(n) }

        var d = 0
        var v = 1
        var y = 0
        var x = 0

        while (true) {
            square[y][x] = v++

            var ny = y + dy[d]
            var nx = x + dx[d]

            if (ny == n || ny == -1 || nx == n || nx == -1 || square[ny][nx] != 0) {
                d = (d + 1) % 3
                ny = y + dy[d]
                nx = x + dx[d]

                if (ny == n || ny == -1 || nx == n || nx == -1 || square[ny][nx] != 0) break
            }

            y = ny
            x = nx
        }

        val answer = IntArray(v - 1)
        var index = 0
        for (y in 0 until n) {
            for (x in 0..y) {
                answer[index] = square[y][x]
                index++
            }
        }

        return answer
    }
}