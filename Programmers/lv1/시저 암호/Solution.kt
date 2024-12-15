// maxGap = z - a
// 소문자 오프셋 = a
// 대문자 오프셋 = A
// totalGap = 문자 - 오프셋 + n
// position = totalGap % (maxGap + 1)

class Solution {
    private fun push(c: Char, n: Int): Char {
        if (!c.isLetter()) return c
        val maxGap = 'z' - 'a'
        val offset = if (c.isUpperCase()) 'A' else 'a'
        val totalGap = c - offset + n
        val position = totalGap % (maxGap + 1)
        return offset + position
    }

    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()
        s.forEach { c ->
            sb.append(push(c, n))
        }
        return sb.toString()
    }
}
