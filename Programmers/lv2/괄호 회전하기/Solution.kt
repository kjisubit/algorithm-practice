// [괄호 회전하기]

// 0 until s.length 만큼 회전
// 여는 괄호 확인될 때마다 짝이되는 괄호를 스택에 push
// 닫는 괄호 확인될 때마다 해당 괄호를 스택에서 제거

class Solution {
    fun solution(s: String): Int {
        var count = 0
        for (i in 0 until s.length) {
            val isPaired = isPaired(i, s)
            if (isPaired) count++
        }
        return count
    }

    private fun isPaired(offset: Int, s: String): Boolean {
        val ad = ArrayDeque<Char>()

        for (i in s.indices) {
            val index = (i + offset) % s.length
            val char = s[index]

            when (char) {
                '(' -> ad.addLast(')')
                '{' -> ad.addLast('}')
                '[' -> ad.addLast(']')
                ')', '}', ']' -> {
                    if (ad.isEmpty()) return false
                    if (char != ad.removeLast()) return false
                }
            }
        }

        return ad.isEmpty()
    }
}