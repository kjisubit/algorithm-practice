// [보석 쇼핑]

// start = 0, end = 0
// 모든 보석이 보일 때까지 end++
// 모든 보석이 보이면 start, end 갱신하고 start++ 반복
// 보석이 한 종류라도 보이지 않을 경우 end++ 반복
// 모든 보석이 보이면 start, end 갱신하고 start++ 반복
// end == gems.size - 1 넘길 시 연산 중단

// 보석은 hashMap으로 관리

class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answerStart = 0
        var answerEnd = gems.size - 1

        var start = 0
        var end = 0

        val gemBox = mutableMapOf<String, Int>()
        gemBox[gems[start]] = 1

        val gemTypeCount = gems.toSet().size

        while (true) {
            if (gemBox.keys.size != gemTypeCount) {
                end++
                if (end > gems.size - 1) break
                gemBox[gems[end]] = (gemBox[gems[end]] ?: 0) + 1
            } else {
                if (end - start < answerEnd - answerStart) {
                    answerEnd = end
                    answerStart = start
                }

                gemBox[gems[start]] = (gemBox[gems[start]] ?: 0) - 1
                if (gemBox[gems[start]] ?: 0 < 1) gemBox.remove(gems[start])
                start++
            }
        }

        return intArrayOf(answerStart + 1, answerEnd + 1)
    }
}