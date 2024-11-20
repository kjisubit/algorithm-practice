// 1. 모든 대문자를 소문자로 치환
// 2. 소문자, 숫자, -, _, . 제외한 모든 문자 제거
// 3. 마침표 2개 연속 이상인 경우 하나로 치환
// 4. 마침표가 처음이나 끝에 위치할 경우 제거
// 5. 빈 문자열인 경우 a 추가
// 6. 16자 이상인 경우, 첫 15개 제외한 나머지 제거한 후 마침표가 끝에 위치한다면 마침표 제거
// 7. 길이가 2 이하인 경우, 마지막 문자를 3이 될 때까지 추가

class Solution {
    fun solution(newId: String): String {
        var answer = newId

        answer = answer.lowercase() // 1
        answer = answer.replace(Regex("[^a-z0-9\\-_.]"), "") // 2
        answer = answer.replace(Regex("\\.{2,}"), ".") // 3
        answer = answer.replace(Regex("^\\.|\\.$"), "") // 4
        if (answer.isEmpty()) answer = "a" // 5
        if (answer.length >= 16) { // 6
            answer = answer.substring(0, 15)
            answer = answer.replace(Regex("\\.$"), "")
        }
        while (answer.length <= 2) { // 7
            answer += answer[answer.length - 1]
        }

        return answer
    }
}