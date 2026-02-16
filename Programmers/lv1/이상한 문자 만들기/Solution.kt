// [이상한 문자 만들기]

// 문자열 순회
// 짝수 플래그 true
// 매 순환 마다 짝수 플래그 뒤집기
// 공백이 될 때마다 짝수 초기화

class Solution {
    fun solution(s: String): String {
        val sb = StringBuilder()

        var isEven = true
        val gap = 'a' - 'A'

        s.forEach { c ->
            if (c == ' ') {
                isEven = true
                sb.append(c)
            }
            else {
                if (isEven) {
                    if (c in 'a'..'z') {
                        sb.append(c - gap)
                    } else {
                        sb.append(c)
                    }
                } else {
                    if (c in 'a'..'z') {
                        sb.append(c)
                    } else {
                        sb.append(c + gap)
                    }
                }

                isEven = !isEven
            }
        }

        return sb.toString()
    }
}