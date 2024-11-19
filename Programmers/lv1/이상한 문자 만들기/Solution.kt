// 문자열 순환
// 짝수 플래그 true
// 매 순환 마다 짝수 플래그 뒤집기
// 공백이 될 때마다 짝수 초기화

class Solution {
    fun solution(s: String): String {
        val builder = StringBuilder()
        var toUpper = true
        s.forEach { c ->
            if (!c.isLetter()) {
                builder.append(c)
                toUpper = true
            } else {
                if (toUpper) {
                    builder.append(c.uppercaseChar())
                } else {
                    builder.append(c.lowercaseChar())
                }
                toUpper = !toUpper
            }
        }
        return builder.toString()
    }
}