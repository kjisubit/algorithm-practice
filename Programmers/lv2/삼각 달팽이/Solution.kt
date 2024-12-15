// n * n 크기의 어레이 생성
// 좌표 이동 문제 -> 변화랑 걔념 사용 -> 필요 방향은 아래, 우, 상좌
// 1. n 을 벗어나거나 다른 숫자를 만날 시, 방향 전환
// -- 아래 -> n 벗어나지 않도록
// -- 우 -> n 벗어나지 않도록
// -- 상좌 -> x, y가 각각 -1 되지 않도록
// 2. 방향 전환 후, 이동 불가할 시

class Solution {
    private val dx = intArrayOf(0, 1, -1)
    private val dy = intArrayOf(1, 0, -1)

    fun solution(n: Int): IntArray {
        val triangle = Array(n) { IntArray(n) }

        var v = 1
        var x = 0
        var y = 0
        var d = 0

        while (true) {
            triangle[y][x] = v++
            var nx = x + dx[d]
            var ny = y + dy[d]

            if (ny == n || ny == -1 || nx == n || nx == -1 || triangle[ny][nx] != 0) {
                d = (d + 1) % 3
                nx = x + dx[d]
                ny = y + dy[d]

                if (ny == n || ny == -1 || nx == n || nx == -1 || triangle[ny][nx] != 0) break
            }

            x = nx
            y = ny
        }

        val result = IntArray(v - 1)
        var index = 0
        for (j in 0 until n) {
            for (i in 0..j) {
                result[index++] = triangle[j][i]
            }
        }
        return result
    }
}