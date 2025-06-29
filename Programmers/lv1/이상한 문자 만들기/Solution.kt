// [이상한 문자 만들기]

// 문자열 순회

// isEven 플래그 초기값 true
// isEven 플래그는 문자열 순회할 때마다 반전
// isEven 플래그는 공백 마주할 때마다 true
// isEven 플래그에 따라 대소문자 변환

class Solution {
    fun solution(s: String): String {
        var isEven = true
        val sb = StringBuilder()

        s.forEach {
            if (it == ' ') {
                isEven = true
                sb.append(' ')
            } else {
                if (isEven) sb.append(it.uppercaseChar())
                else sb.append(it.lowercaseChar())
                isEven = !isEven
            }
        }
        return sb.toString()
    }
}