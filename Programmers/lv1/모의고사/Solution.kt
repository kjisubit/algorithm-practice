// [모의고사]

// 메서드 1
// 각 시험자에 대하여 정답 맞춘 개수 확인

class Solution {
    private val patterns = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5),
    )

    private fun countAnswer(answers: IntArray, pattern: IntArray): Int {
        var count = 0
        for (i in answers.indices) {
            val patternIndex = i % pattern.size
            if (answers[i] == pattern[patternIndex]) count++
        }

        return count
    }

    fun solution(answers: IntArray): IntArray {
        var max = 0
        val scores = IntArray(3)

        patterns.forEachIndexed { i, pattern ->
            val score = countAnswer(answers, pattern)
            scores[i] = score
            if (score >= max) max = score
        }

        return (0..2).filter { scores[it] == max }.map { it + 1 }.toIntArray()
    }
}