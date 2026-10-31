// [괄호 회전하기]

// 짝 탐색 문제 -> 스택

// 좌측 문자열 회전 -> 오프셋으로 구현
// - 한 칸 이동할 때마다 오프셋 1 추가

// 회전 후 올바른 괄호 여부 체크
// - 문자열 순회하며 스택에 저장
// - 닫힌 괄호 마주칠 때마다 열린 괄호와 매칭되면 스택에서 제거
// - 스택이 공백이 될 때마다 카운트

class Solution {
    fun solution(s: String): Int {
        var count = 0
        for (offset in 0 until s.length) {
            if (checkValidation(s, offset)) count++
        }

        return count
    }

    private fun checkValidation(s: String, offset: Int): Boolean {
        val ad = ArrayDeque<Char>()

        for (i in 0 until s.length) {
            val index = (i + offset) % s.length
            val c = s[index]
            when (c) {
                '(', '{', '[' -> ad.addLast(c)
                ')' -> {
                    if (ad.isNotEmpty() && ad.last() == '(') ad.removeLast()
                    else ad.addLast(c)
                }
                '}' -> {
                    if (ad.isNotEmpty() && ad.last() == '{') ad.removeLast()
                    else ad.addLast(c)
                }
                ']' -> {
                    if (ad.isNotEmpty() && ad.last() == '[') ad.removeLast()
                    else ad.addLast(c)
                }
            }
        }

        return ad.isEmpty()
    }
}