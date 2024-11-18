// 2차원 배열 문제 -> 좌표 변화량으로 해결
// n * n 크기 어레이 생성
// 방향전환(아래, 우측, 상단좌측) 반복
// 방향전화 직후, 어레이를 벗어나거나 다른 숫자가 이미 채워져있는 경우 종료
// 어레이의 각 로우를 하나의 행으로 통합

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

            if (ny == n || nx == n || triangle[ny][nx] != 0) {
                d = (d + 1) % 3
                nx = x + dx[d]
                ny = y + dy[d]

                if (nx == n || triangle[ny][nx] != 0) break
            }

            x = nx
            y = ny
        }

        val result = IntArray(v - 1)
        var index = 0
        for (i in 0 until n) {
            for (j in 0..i) {
                result[index++] = triangle[i][j]
            }
        }

        return result
    }
}