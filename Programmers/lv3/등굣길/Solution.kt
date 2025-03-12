class Solution {
    private val mem = Array(101) { IntArray(101) { -1 } }

    private fun count(x: Int, y: Int, w: Int, h: Int, isPuddle: Array<BooleanArray>): Int {
        if (x > w || y > h) return 0
        if (isPuddle[y][x]) return 0

        if (mem[x][y] != -1) return mem[x][y]
        if (x == w && y == h) return 1

        // 값이 지나치게 커질 수 있어 나머지 연산을 수행하도록 한 것
        // 모듈러 연산의 성질 적용 (A + B) % C = ( ( A % C ) + ( B % C) ) % C
        // 두 항의 합에 대한 모듈러 연산 결과 = 각 항에 대한 모듈러 연산을 수행한 결과를 더한 후, 해당 결과에 모듈러 연산을 수행한 결과와 같다.
        val total = count(x + 1, y, w, h, isPuddle) + count(x, y + 1, w, h, isPuddle)
        mem[x][y] = total % 1000000007
        return mem[x][y]
    }

    fun solution(m: Int, n: Int, puddles: Array<IntArray>): Int {
        val isPuddle = Array(n + 1) { BooleanArray(m + 1) }
        for (p in puddles) {
            isPuddle[p[1]][p[0]] = true
        }
        return count(1, 1, m, n, isPuddle)
    }
}