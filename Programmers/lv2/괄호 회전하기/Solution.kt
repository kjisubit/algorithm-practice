// 회전 가능한 모든 오프셋으로 루프
// 각 오프셋 만큼 회전시킨 결과가 괄호 내용에 부합하는지 확인
class Solution {
    private fun isCorrect(s: String, offset: Int): Boolean {
        val arrayDeque = ArrayDeque<Char>()

        for (i in s.indices) {
            val c = s[(i + offset) % s.length]

            when (c) {
                '(' -> arrayDeque.addLast(')')
                '{' -> arrayDeque.addLast('}')
                '[' -> arrayDeque.addLast(']')
                ')', '}', ']' -> {
                    if (arrayDeque.isEmpty()) return false
                    if (arrayDeque.removeLast() != c) return false
                }
            }
        }

        return arrayDeque.isEmpty()
    }

    fun solution(s: String): Int {
        var count = 0
        for (offset in s.indices) {
            if (isCorrect(s, offset)) {
                count++
            }
        }
        return count
    }
}