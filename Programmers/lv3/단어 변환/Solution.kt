// [단어 변환]

// 최단 과정 -> BFS

// 클래스: 상태 나타내기용
// step(변환 횟수), word(단어)

// 메서드: 단어 변환 가능 여부 확인
// 문자가 한 개만 차이나는 경우 변환 가능

class Solution {
    private class State(val word: String, val step: Int)

    private fun isConvertible(start: String, dst: String): Boolean {
        var count = 0
        for (i in start.indices) {
            if (start[i] != dst[i]) count++
        }

        return count == 1
    }

    fun solution(begin: String, target: String, words: Array<String>): Int {
        val isVisited = BooleanArray(words.size)

        val ad = ArrayDeque<State>()
        ad.addFirst(State(begin, 0))

        while (ad.isNotEmpty()) {
            val state = ad.removeFirst()

            if (state.word == target) return state.step

            for (i in words.indices) {
                val next = words[i]

                if (!isConvertible(state.word, next)) continue

                if (isVisited[i]) continue
                isVisited[i] = true

                ad.addLast(State(next, state.step + 1))
            }
        }

        return 0
    }
}