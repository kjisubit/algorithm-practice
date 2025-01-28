// 큰 부분 문제를 작은 부분 문제로 표현 가능하다 -> 재귀
// 상태
// 종료
// 점화식
// (공백으로 시작하는 토큰) = (token[index] 로 시작하는 토큰) + (-로 시작하는 토큰)
// (index, "prefix") = (index + 1, "prefix "+ token[index]) + (index + 1, "-" + token[index])

class Solution {
    private fun forEachKey(index: Int, prefix: String, tokens: Array<String>, action: (String) -> Unit) {
        if (index == tokens.size - 1) {
            action(prefix)
            return
        }

        forEachKey(index + 1, prefix + tokens[index], tokens, action)
        forEachKey(index + 1, "$prefix-", tokens, action)
    }

    private fun buildScoresMap(info: Array<String>): Map<String, MutableList<Int>> {
        val scoresMap: MutableMap<String, MutableList<Int>> = HashMap()

        for (s in info) {
            val tokens = s.split(" ".toRegex()).toTypedArray()
            val score = tokens[tokens.size - 1].toInt()
            forEachKey(0, "", tokens) { key: String ->
                scoresMap.putIfAbsent(key, ArrayList())
                scoresMap[key]!!.add(score)
            }
        }

        for (list in scoresMap.values) {
            list.sort()
        }

        return scoresMap
    }

    private fun binarySearch(score: Int, scores: List<Int>): Int {
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

    private fun count(query: String, scoresMap: Map<String, MutableList<Int>>): Int {
        val tokens = query.split(" (and )?".toRegex()).toTypedArray()
        val key = tokens.copyOf(tokens.size - 1).joinToString("")

        if (!scoresMap.containsKey(key)) return 0
        val scores: List<Int> = scoresMap[key]!!

        val score = tokens[tokens.size - 1].toInt()

        return scores.size - binarySearch(score, scoresMap[key]!!)
    }

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val scoresMap = buildScoresMap(info)

        val answer = IntArray(query.size)
        for (i in answer.indices) {
            answer[i] = count(query[i], scoresMap)
        }

        return answer
    }
}