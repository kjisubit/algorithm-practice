// 삼각 달팽이

// 2차원 배열 생성하고 0으로 채우기
// 2차원 배열 범위 초과 시, 방향 전환 (하, 우, 상좌)
// 방향 전환이 연속 2번 일어날 경우 종료

class Solution {
    private val dx = intArrayOf(0, 1, -1)
    private val dy = intArrayOf(1, 0, -1)

    private var v = 1
    private var d = 0
    private var x = 0
    private var y = 0

    fun solution(n: Int): IntArray {
        val array = Array(n) { IntArray(n) { 0 } }
        var blockCounter = 0

        while (true) {
            if (blockCounter == 2) break
            array[y][x] = v
            val tempX = x + dx[d]
            val tempY = y + dy[d]

            // 배열 크기 초과, 혹은 이미 값이 존재할 경우 방향 전환)
            if (tempY == n || tempY < 0 || tempX == n || tempX < 0 || array[tempY][tempX] > 0) {
                blockCounter++
                d = (d + 1) % 3
            } else {
                blockCounter = 0
                x = tempX
                y = tempY
                v++
            }
        }

        val numList = mutableListOf<Int>()
        for (i in 0 until n) {
            for (j in 0 until i + 1) {
                val num = array[i][j]
                numList.add(num)
            }
        }

        return numList.toIntArray()
    }
}