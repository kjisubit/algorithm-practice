// 문자열 순회
// 각 문자에 대하여 n 만큼 밀어내는 별도의 함수 생성
// 밀어낸 문자 취합하여 리턴

class Solution {
    private fun push(c: Char, n: Int): Char {
        if (!c.isLetter()) return c
        val maxGap = 'Z'.code - 'A'.code + 1

        val offset = if (c.isUpperCase()) 'A' else 'a'
        val position = (c.code + n - offset.code) % maxGap
        return offset + position
    }

    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()
        s.forEach {
            sb.append(push(it, n))
        }
        return sb.toString()
    }
}
