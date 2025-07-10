// [신규 아이디 추천]

// 정규식 사용

class Solution {
    fun solution(newId: String): String {
        var answer = newId
        answer = answer.lowercase()
        answer = answer.replace(Regex("[^a-z0-9\\-_.]"), "")
        answer = answer.replace(Regex("\\.{2,}"), ".")
        answer = answer.replace(Regex("^\\.|\\.$"), "")
        if (answer.isEmpty()) answer = "a"
        if (answer.length >= 16) {
            answer = answer.substring(0, 15)
            answer = answer.replace(Regex("\\.$"), "")
        }
        val last = answer[answer.length - 1]
        while (answer.length <= 2) {
            answer += last
        }
        return answer
    }
}