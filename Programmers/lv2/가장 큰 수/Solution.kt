// [가장 큰 수]

// 1. 두 수를 이어붙인 결과에 따라 정렬 수행

class Solution {
    fun solution(numbers: IntArray): String {
        val regex = Regex("^0{2,}")
        return numbers.sortedWith { a, b ->
            val ordered = (a.toString() + b.toString()).toInt()
            val reversed = (b.toString() + a.toString()).toInt()
            reversed.compareTo(ordered)
        }.joinToString("").replace(regex, "0")
    }
}