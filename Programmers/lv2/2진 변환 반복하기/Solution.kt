// 문자열에서 모든 0 제거
// 문자열 길이를 2진법으로 변경
// 2진 변환 횟수, 제거된 0 개수 리턴

class Solution {
    fun solution(s: String): IntArray {
        var binaryString = s
        var removed = 0
        var loop = 0

        while (binaryString != "1") {
            val zeros = countZeros(binaryString)
            removed += zeros
            loop++

            val ones = binaryString.length - zeros
            binaryString = ones.toString(2)
        }
        return intArrayOf(loop, removed)
    }

    private fun countZeros(s: String): Int {
        return s.count { it == '0' }
    }
}