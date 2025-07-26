// [카펫]

// w * h = brown + yellow
// brown = w * 2 + (h - 2) * 2
// yellow = (w - 2) * (h - 2)
// 3 <= w <= maxWidth + 2
// 3 <= h <= maxWidth + 2
// maxWidth = brown 으로 표현 가능한 최대 길이 = (5000 - 2) / 2

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        val maxWidth = (5000 - 2) / 2
        for (w in 3..maxWidth + 2) {
            for (h in 3..w) {
                val firstRule = yellow == (w - 2) * (h - 2)
                val secondRule = brown == (w - 2) * 2 + h * 2

                if (firstRule && secondRule) {
                    return intArrayOf(w, h)
                }
            }
        }
        return intArrayOf(0, 0)
    }
}