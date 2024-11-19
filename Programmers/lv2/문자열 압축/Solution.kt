// (subString()을 사용하면 쉽게 풀리는 문제임에 유의)

// 1 ~ s.length 사이를 반복하여 압축 시도하기 위해 문자열 쪼개기 함수 생성
// 매 압축 마다 다음 로직 시도하기 위해 압축 함수 생성
// 타겟 토큰이 이전 토큰과 동일하지 않을 경우 -> 문자열에 숫자 및 이전 토큰 추가, 카운트 초기화
// 타겟 토큰이 이전 토큰과 동일할 경우 -> 카운트 추가

class Solution {
    fun solution(s: String): Int {
        var min = Int.MAX_VALUE
        for (length in 1..s.length) {
            val tokens = split(s, length)
            val compressed = compress(tokens)
            if (compressed < min) min = compressed
        }
        return min
    }

    private fun split(source: String, length: Int): List<String> {
        val tokens = mutableListOf<String>()
        var startIndex = 0
        while (startIndex < source.length) {
            var endIndex = startIndex + length
            if (endIndex > source.length) endIndex = source.length
            tokens.add(source.substring(startIndex, endIndex))
            startIndex += length
        }
        return tokens
    }

    private fun compress(tokens: List<String>): Int {
        val builder = StringBuilder()

        var last = ""
        var count = 0
        for (token in tokens) {
            if (token == last) {
                count++
            } else {
                if (count > 1) builder.append(count)
                builder.append(last)
                last = token
                count = 1
            }
        }

        if (count > 1) builder.append(count)
        builder.append(last)

        return builder.length
    }
}