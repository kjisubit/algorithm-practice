// [문자열 압축]

// 메서드 1 - 문자열 쪼개기 메서드
// 메서드 2 - 문자열 압축 메서드

class Solution {
    fun solution(s: String): Int {
        var length = s.length
        for (i in 1..s.length) {
            val compressed = compress(s, i)
            if (compressed < length) length = compressed
        }
        return length
    }

    private fun split(s: String, length: Int): List<String> {
        return s.chunked(length)
    }

    private fun compress(s: String, length: Int): Int {
        val sb = StringBuilder()

        var last = ""
        var count = 0

        for (token in split(s, length)) {
            if (last == token) {
                count++
            } else {
                if (count > 1) sb.append(count)
                sb.append(last)

                count = 1
                last = token
            }
        }

        if (count > 1) sb.append(count)
        sb.append(last)

        return sb.length
    }
}