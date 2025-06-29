// [시저 암호]

// 메서드 1 -> char에 대하여 push

class Solution {
    private fun push(c: Char, n: Int): Char {
        if (!c.isLetter()) return ' '

        val alphabetCount = 'z' - 'a' + 1
        val offset = if (c.isUpperCase()) 'A' else 'a'
        val position = (c.code - offset.code + n) % alphabetCount

        return offset + position
    }

    fun solution(s: String, n: Int): String {
        s.map {
            push(it, n)
        }.joinToString("")
        return s
    }
}