// [보석 쇼핑]

// 투 포인터 사용

class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answerStart = 0
        var answerEnd = gems.size - 1
        val gemSet = gems.toSet()

        var pointStart = 0
        var pointEnd = 0

        val gemMap = mutableMapOf<String, Int>()
        gemMap[gems[pointStart]] = 1

        while (pointStart < gems.size) {
            if (gemMap.keys.size == gemSet.size) {
                if (pointEnd - pointStart < answerEnd - answerStart) {
                    answerStart = pointStart
                    answerEnd = pointEnd
                }

                gemMap[gems[pointStart]] = gemMap[gems[pointStart]]!! - 1
                if (gemMap[gems[pointStart]] == 0) gemMap.remove(gems[pointStart])
                pointStart++
            } else if (pointEnd < gems.size - 1) {
                pointEnd++
                gemMap[gems[pointEnd]] = gemMap.getOrDefault(gems[pointEnd], 0) + 1
            } else {
                break
            }
        }

        return intArrayOf(answerStart + 1, answerEnd + 1)
    }
}