// [가장 큰 수]

// 두 수를 이어 붙였을 때, 더 큰 수가 나오도록 정렬
// 숫자의 크기 기준으로 정렬하는 대신
// 앞 자리에 배치되었을 때 값 상승 효과가 큰 순서대로 정렬 필요

class Solution {
    fun solution(numbers: IntArray): String {
        return numbers.map { it.toString() }.sortedWith { a, b ->
            val order = a + b
            val reversed = b + a
            reversed.toInt() - order.toInt()
        }.joinToString("").replace(Regex("^0{1,}"), "0")
    }
}