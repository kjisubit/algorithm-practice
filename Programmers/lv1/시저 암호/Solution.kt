// 메서드 1 -> char에 대하여 push

class Solution {
    private fun push(c: Char, n: Int): Char {
        if (c == ' ') return ' '

        val offset = if (c.isUpperCase()) 'A' else 'a'
        val alphabetSize = 'z' - 'a' + 1

        val position = (c.code + n - offset.code) % alphabetSize
        return offset + position
    }

    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()

        for (c in s) {
            val pushed = push(c, n)
            sb.append(pushed)
        }

        return sb.toString()
    }
}