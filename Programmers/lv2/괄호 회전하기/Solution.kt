// [괄호 회전하기]

// 1. 문자열 돌리기
// 매 케이스마다 오프셋 변경 (0 until s.length)

// 2. 각 케이스에 대한 올바른 괄호 생성 여부 판단
// 문자열 순회하며 열린 괄호 만날 때마다 push 닫힌 괄호 만날 때마다 pop

class Solution043 {
    fun solution(s: String): Int {
        var count = 0
        for (offset in 0 until s.length) {
            if (checkValidation(offset, s)) count++
        }

        return count
    }

    private fun checkValidation(offset: Int, s: String): Boolean {
        var ad = ArrayDeque<Char>()

        for (i in 0 until s.length) {
            val index = (i + offset) % s.length
            var c = s[index]

            when (c) {
                '(' -> ad.addLast(')')
                '[' -> ad.addLast(']')
                '{' -> ad.addLast('}')
                else -> {
                    if (ad.isEmpty()) return false
                    if (c != ad.removeLast()) return false
                }
            }
        }

        return ad.isEmpty()
    }
}