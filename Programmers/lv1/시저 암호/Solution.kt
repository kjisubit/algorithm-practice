// [시저 암호]

// 문자열 순회 -> 각 문자 push

class Solution {
    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()

        for (c in s) {
            sb.append(push(c, n))
        }
        return sb.toString()
    }

    private fun push(c: Char, n: Int): Char {
        val range = 'z' - 'a' + 1
        if (c in 'a'..'z') {
            val offset = c - 'a'
            println(offset)
            val pushedOffset = offset + n
            return 'a' + (offset + n) % range
        } else if (c in 'A'..'Z') {
            val offset = c - 'A'
            val pushedOffset = offset + n
            return 'A' + (offset + n) % range
        } else {
            return ' '
        }
    }
}