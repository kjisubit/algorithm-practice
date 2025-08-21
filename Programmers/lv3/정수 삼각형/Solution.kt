// [정수 삼각형]

// 점화식
// -> (y, x) = (y, x) + max((y + 1, x - 1), (y + 1, x + 1))

// 종료 조건
// -> 삼각형 최대 높이 도달

// triangle 높이가 최대 500이고 반복되는 부분문제가 많음
// -> (y, x)에 대한 메모이제이션 필요

import kotlin.math.*

class Solution {
    private val mem = Array(500) { IntArray(500) { -1 } }

    fun solution(triangle: Array<IntArray>): Int {
        return getMax(0, 0, triangle)
    }

    private fun getMax(y: Int, x: Int, triangle: Array<IntArray>): Int {
        if (mem[y][x] != -1) return mem[y][x]
        if (y == triangle.size) return 0

        mem[y][x] = triangle[y][x] + max(
            getMax(y + 1, x, triangle),
            getMax(y + 1, x + 1, triangle)
        )

        return mem[y][x]
    }
}