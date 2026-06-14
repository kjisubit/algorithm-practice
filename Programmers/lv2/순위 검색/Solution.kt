// 전처리 필요
// info 카테고리 분류 -> 4 * 3 * 3 * 3 = 108
// 한 명의 지원자가 속할 수 있는 카테고리 -> 2^4 = 16
// 한 카테고리에 속할 수 있는 최대 지원자 수 = 5000

// 메서드 1 - 카테고리화
// 메서드 2 - 메서드 2에서 호출할 카테고리 키 생성 재귀
// 메서드 3 - 기준 점수 이상 지원자 카운트
// 메서드 4 - 메서드 3에서 호출할 이진 탐색

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val scoreMap = buildScoreMap(info)
        val answer = IntArray(query.size)
        for (i in query.indices) {
            answer[i] = count(scoreMap, query[i])
        }
        return answer
    }

    private fun buildScoreMap(info: Array<String>): MutableMap<String, MutableList<Int>> {
        var scoreMap = mutableMapOf<String, MutableList<Int>>()
        for (i in info) {
            val tokens = i.split(" ")
            val score = tokens.last().toInt()
            val action: (String) -> Unit = { key: String ->
                scoreMap.putIfAbsent(key, mutableListOf())
                scoreMap[key]!!.add(score)
            }
            buildKey("", 0, tokens, action)
        }

        for (list in scoreMap.values) {
            list.sort()
        }
        return scoreMap
    }

    private fun buildKey(prefix: String, index: Int, tokens: List<String>, action: (String) -> Unit) {
        if (index == tokens.size - 1) {
            action(prefix)
            return
        }

        buildKey(prefix + tokens[index], index + 1, tokens, action)
        buildKey(prefix + "-", index + 1, tokens, action)
    }

    private fun count(scoreMap: MutableMap<String, MutableList<Int>>, query: String): Int {
        val regex = " (and )?".toRegex()
        val tokens = query.split(regex)

        val key = tokens.slice(0..tokens.size - 2).joinToString("")
        val score = tokens.last().toInt()

        val scores = scoreMap[key] ?: return 0

        return scores.size - binarySearch(score, scores)
    }

    // score(target) 보다 크거나 같은 값 중 "가장 작은 값" 구하기 [start, end]
    private fun binarySearch(target: Int, range: List<Int>): Int {
        var start = 0
        var end = range.size - 1

        // 원소가 한 개만 남을 때까지 반복
        // [start, end] 원소 개수는 end - start + 1
        while (end - start + 1 > 1) {
            val mid = (start + end) / 2

            if (range[mid] >= target) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        // score(target) 보다 크거나 같은 값 자체가 존재하지 않을 경우 예외 처리
        if (range[start] < target) return range.size

        return start
    }
}