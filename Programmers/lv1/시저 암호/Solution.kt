// 1. 문자열 순회

// 2. 대/소문자, 공백에 맞춰 push 로직 분기

class Solution {
    private fun push(c: Char, n: Int): Char {
        if (c == ' ') return ' ' // 공백

        val range = 'z' - 'a' + 1
        val startPoint = if (c in 'a'..'z') 'a' else 'A'

        val pushed = startPoint + (c.code - startPoint.code + n) % range
        return pushed
    }

    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()

        for (c in s) {
            sb.append(push(c, n))
        }

        return sb.toString()
    }
}
