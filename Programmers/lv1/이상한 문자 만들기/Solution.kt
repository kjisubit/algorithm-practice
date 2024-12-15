// toUpper 플래그 true
// 이후 플래그 뒤집기 반복
// 공백 만날 시, 무조건 플래그 true

class Solution {
    fun solution(s: String): String {
        val sb = StringBuilder()
        var toUpper = true
        s.forEach { c ->
            if (c.isLetter()) {
                if (toUpper) {
                    sb.append(c.uppercaseChar())
                } else {
                    sb.append(c.lowercaseChar())
                }
                toUpper = !toUpper
            } else {
                toUpper = true
                sb.append(c)
            }
        }
        return sb.toString()
    }
}