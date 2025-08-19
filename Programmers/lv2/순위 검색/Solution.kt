// [순위 검색]

// query 배열을 순회하며 info 배열 조회 시, 시간 복잡도 1억 초과하므로 전처리 필요

// 메서드 1
// -> info 로 생성 가능한 모든 조합을 key로 같는 map 생성

// 메서드 2
// -> 이후 query의 각 요소에 대응되는 스코어 목록 산출

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val scoreMap = buildScoreMap(info)

        val answer = IntArray(query.size)
        for (i in query.indices) {
            answer[i] = count(scoreMap, query[i])
        }

        return answer
    }

    private fun buildScoreMap(info: Array<String>): Map<String, MutableList<Int>> {
        val scoreMap = mutableMapOf<String, MutableList<Int>>()

        for (i in info) {
            val tokens = i.split(" ")
            val score = tokens.last().toInt()
            forEachKey(0, "", tokens) { key: String ->
                scoreMap.putIfAbsent (key, mutableListOf())
                scoreMap[key]!!.add(score)
            }
        }

        for (list in scoreMap.values) {
            list.sort()
        }

        return scoreMap
    }

    private fun forEachKey(index: Int, prefix: String, tokens: List<String>, action: (String) -> Unit) {
        if (index == tokens.size - 1) {
            action (prefix)
            return
        }

        forEachKey(index + 1, prefix + tokens[index], tokens, action)
        forEachKey(index + 1, "$prefix-", tokens, action)
    }

    private fun count(scoreMap: Map<String, MutableList<Int>>, query: String): Int {
        val tokens = query.split(Regex(" (and )?"))
        val key = tokens.slice(0..tokens.size - 2).joinToString("")
        val score = tokens.last().toInt()

        if (!scoreMap.containsKey(key)) return 0
        val scores: List<Int> = scoreMap[key]!!

        return scores.size - binarySearch(score, scoreMap[key]!!)
    }

    private fun binarySearch (score: Int, scores: List<Int>): Int {
        var start = 0 // inclusive
        var end = scores.size - 1 // inclusive

        while (end > start) {
            val mid = (start + end) / 2

            if (scores[mid] >= score) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        if (scores[start] < score) {
            return scores.size
        }
        return start
    }
}