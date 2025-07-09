// [문자열 다루기 기본]

class Solution {
    fun solution(s: String): Boolean {
        return s.matches(Regex("[0-9]{4}|[0-9]{6}"))
    }
}