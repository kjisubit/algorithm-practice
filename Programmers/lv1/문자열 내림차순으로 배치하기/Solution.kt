// [문자열 내림차순으로 배치하기]

class Solution {
    fun solution(s: String): String {
        return s.toList().sorted().reversed().joinToString("")
    }
}