// [H-Index]

// 입력값 내림차순 정렬 reversed

// 가장 큰 h 후보로부터 가장 작은 후보인 0에 대하여 반복 연산 수행
// - for (h in citations.size downTo 0)
// - case1: reversed[h - 1] >= h
// - case2: reversed[h] <= h
// - 인덱스 i + 1 부터 마지막 까지의 원소 개수가 count 이하일 경우
// - h 인덱스 갱신

class Solution {
    fun solution(citations: IntArray): Int {
        var answer = 0
        var reversed = citations.sorted().reversed()
        for (h in citations.size downTo 1) {
            val case1 = reversed[h - 1] >= h
            val case2 = citations.size - h < h
            if (case1 && case2) {
                answer = h
                break
            }
        }
        return answer
    }
}