// [문자열 내 마음대로 정렬하기]

// 두 기준 충족 필요 -> 인덱스가 가리키는 문자 기준으로 오름차순 + 문자열 사전순 오름 차순

class Solution {
    fun solution(strings: Array<String>, n: Int): Array<String> {
        return strings.sortedWith(compareBy({ it[n] }, { it })).toTypedArray()
    }
}