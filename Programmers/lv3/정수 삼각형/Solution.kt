class Solution {
    private val mem = Array(500) { IntArray(500) { -1 } }

    private fun max(x: Int, y: Int, triangle: Array<IntArray>): Int {
        if (y == triangle.size) return 0
        if (mem[x][y] != -1) return mem[x][y]

        mem[x][y] = triangle[y][x] + maxOf(
            max(x, y + 1, triangle),
            max(x + 1, y + 1, triangle)
        )

        return mem[x][y]
    }

    fun solution(triangle: Array<IntArray>): Int {
        return max(0, 0, triangle)
    }
}