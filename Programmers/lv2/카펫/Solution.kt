// 가로 길이 x, 세로 길이 y라 가정
// 테두리 개수 = brown = 2x + 2y - 4
// 가운데 영역 개수 = yellow = (x - 2) * (y - 2)
// 총 개수 = x * y

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        for (x in 3..5000) {
            for (y in 3..x) {
                val brownMatched = brown == (2 * x + 2 * y - 4)
                val yellowMatched = yellow == (x - 2) * (y - 2)
                if (brownMatched && yellowMatched) return intArrayOf(x, y)
            }
        }
        return intArrayOf(0, 0)
    }
}