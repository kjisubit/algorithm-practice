// [문자열 내림차순으로 배치하기]

// 정렬 메커니즘에 대한 이해가 필요한 문제 (메서드 암기 X)

class Solution {
    fun solution(s: String): String {
        return s.toList().sortedWith { a, b -> b.compareTo(a) }.joinToString("")
    }
}