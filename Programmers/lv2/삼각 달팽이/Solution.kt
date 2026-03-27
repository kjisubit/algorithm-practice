// [삼각 달팽이]

// 좌표 이동 문제

// 프로세스
// -- n * n 어레이 생성
// -- 최대 범위 도달, 혹은 이미 존재하는 값 마주할 시 방향 변경
// -- 방향 변경 연속 2번 실패 시 작업 종료

class Solution {
    // 하, 우, 상좌
    private val dx = intArrayOf(0, 1, -1)
    private val dy = intArrayOf(1, 0, -1)

    fun solution(n: Int): IntArray {
        val array = Array(n) { IntArray(n) }

        var d = 0
        var v = 1
        var x = 0
        var y = 0

        array[y][x] = v

        while (true) {
            // 신규 좌표 검증
            var ny = y + dy[d]
            var nx = x + dx[d]
            if (ny == n || ny < 0 || nx == n || nx < 0 || array[ny][nx] != 0) {
                d = (d + 1) % 3
                ny = y + dy[d]
                nx = x + dx[d]

                if (ny == n || ny < 0 || nx == n || nx < 0 || array[ny][nx] != 0) break
            }

            y = ny
            x = nx
            array[y][x] = ++v
            println("$y $x $v")
        }

        val answer = mutableListOf<Int>()
        for (y in 0..n - 1) {
            for (x in 0..y) {
                val num = array[y][x]
                answer.add(num)
            }
        }
        return answer.toIntArray()
    }
}