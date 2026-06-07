// [모의고사]

// 1. 수포자 순회
// 2. 각 수포자 정답 카운트 후 어레이에 저장

class Solution {
    val patterns = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )

    private fun countAnswers(answers: IntArray, pattern: IntArray): Int {
        var count = 0
        answers.forEachIndexed { i, number ->
            if (number == pattern[i % pattern.size]) count++
        }
        return count
    }

    fun solution(answers: IntArray): IntArray {
        var maxValue = 0

        val countArray = IntArray(3)
        patterns.forEachIndexed { i, pattern ->
            countArray[i] = countAnswers(answers, pattern)
            if (countArray[i] > maxValue) maxValue = countArray[i]
        }

        return countArray.indices
            .filter { countArray[it] == maxValue }
            .map { it + 1 }
            .sorted().toIntArray()
    }
}