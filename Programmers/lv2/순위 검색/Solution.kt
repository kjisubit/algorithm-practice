// [순위 검색]

// info의 모든 데이터를 카테고리화하여 해시 테이블 탐색 가능한 구조로 변경
// - MutableMap<String, MutableList<Int>>

// query 에서 요구하는 카테고리에 속한 점수 들 중, query 점수가 어디에 위치해 있는지를 파라매트릭 써치로 검색
// - x점 이상인 수 중에서 가장 작은 수가 가리키는 인덱스 찾기
// - 전체 길이 - 가장 작은 수가 가리키는 인덱스 = x 점 이상인 지원자의 수

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val searchMap = buildSearchMap(info)

        val answer = IntArray(query.size)
        query.forEachIndexed { i, q ->
            answer[i] = count(searchMap, q)
        }

        return answer
    }

    private fun count(searchMap: MutableMap<String, MutableList<Int>>, query: String): Int {
        val regex = Regex("( and){0,} ")
        val token = query.split(regex)
        val queryKey = token.slice(0..3).joinToString("")
        val score = token[4].toInt()

        if (searchMap[queryKey] == null) return 0
        else return searchMap[queryKey]!!.size - getLowerBoundIndex(score, searchMap[queryKey]!!)
    }

    private fun getLowerBoundIndex(score: Int, scoreList: MutableList<Int>): Int {
        var start = 0
        var end = scoreList.size - 1

        while (end - start + 1 > 1) {
            var mid = (start + end) / 2

            if (scoreList[mid] >= score) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        if (scoreList[start] < score) return scoreList.size

        return start
    }

    private fun buildSearchMap(info: Array<String>): MutableMap<String, MutableList<Int>> {
        val searchMap = mutableMapOf<String, MutableList<Int>>()

        for (s in info) {
            val tokens = s.split(" ")
            val infos = tokens.slice(0..3)
            val score = tokens[4].toInt()
            val action: (String) -> Unit = { s ->
                searchMap.putIfAbsent(s, mutableListOf<Int>())
                searchMap[s]!!.add(score)
            }
            fillSearchMap(infos, "", 0, action)
        }

        searchMap.values.forEach {
            it.sort()
        }

        return searchMap
    }

    private fun fillSearchMap(tokens: List<String>, prefix: String, index: Int, action: (String) -> Unit) {
        if (index == 4) {
            action(prefix)
            return
        }

        fillSearchMap(tokens, prefix + tokens[index], index + 1, action)
        fillSearchMap(tokens, prefix + "-", index + 1, action)
    }
}