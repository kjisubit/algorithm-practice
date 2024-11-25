// 모든 인덱스를 더한 후, Set 에 저장
// 이후 sort

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val set = mutableSetOf<Int>()
        for (i in numbers.indices) {
            for (j in i + 1 until numbers.size) {
                set.add(numbers[i] + numbers[j])
            }
        }
        return set.sorted().toIntArray()
    }
}