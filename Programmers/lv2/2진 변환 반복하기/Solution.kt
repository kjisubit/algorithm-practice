// [이진 변환 반복하기]

// 메서드 1 - 문자열에서 0 개수 카운트

// 문자열 길이 - 메서드 1 결과 = 1의 길이
// 1의 길이를 10진수 정수로 표현
// 결과가 1이 될 때가지 위 과정 반복

class Solution {
    fun solution(s: String): IntArray {
        var binaryString = s

        var transformCounter = 0
        var zeros = 0

        while (binaryString != "1") {
            val zeroCount = countZero(binaryString)
            zeros += zeroCount
            transformCounter++

            val ones = binaryString.length - zeroCount
            binaryString = ones.toString(2)
        }

        return intArrayOf(transformCounter, zeros)
    }

    private fun countZero(s: String): Int {
        return s.count { it == '0' }
    }
}