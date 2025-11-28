// [가장 큰 수]

// 두 수를 정방향 or 역방향으로 각각 이어붙인 후, 결과가 더 커지는 순으로 정렬 수행

class Solution {
    fun solution(numbers: IntArray): String {
        val regex = Regex("^0+")
        return numbers.map { it.toString() }.sortedWith { a, b ->
            val ordered = (a + b).toInt()
            val reversed = (b + a).toInt()
            reversed - ordered
        }.joinToString("").replace(regex, "0")
    }
}