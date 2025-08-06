// [두 개 뽑아서 더하기]

// 모든 경우의 수를 Set에 저장
// 이후 정렬

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val set = mutableSetOf<Int>()
        for (i in 0 until numbers.size) {
            for (j in i + 1 until numbers.size) {
                set.add (numbers[i] + numbers[j])
            }
        }
        val answer = set.sorted().toIntArray()
        return answer
    }
}