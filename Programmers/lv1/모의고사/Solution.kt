// [모의고사]

// 수포자 정답 패턴 별 answer 순회 후 정답 카운트 기록

class Solution {
    private val patterns = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )

    fun solution(answers: IntArray): IntArray {
        var maxValues = Int.MIN_VALUE

        var scoreArray = IntArray(3)
        for (i in scoreArray.indices) {
            scoreArray[i] = getScore(i, answers)
            if (scoreArray[i] > maxValues) maxValues = scoreArray[i]
        }

        return scoreArray.indices
            .filter { scoreArray[it] == maxValues }
            .map { it + 1 }.toIntArray()
    }

    private fun getScore(testerIndex: Int, answers: IntArray): Int {
        var count = 0
        val pattern = patterns[testerIndex]
        for (i in answers.indices) {
            val patternIndex = i % pattern.size
            if (answers[i] == pattern[patternIndex]) count++
        }

        return count
    }
}