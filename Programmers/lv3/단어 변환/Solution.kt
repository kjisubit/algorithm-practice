// [단어 변환]

// 최단 과정 찾기 -> BFS -> Queue

// words에 convert 가능한 단어 존재할 경우
// - count+
// - 방문 체크
// - q에 추가

// 메서드
// - 알파벳 한 글자 차이 여부 확인

// 클래스
// - 멤버: 단어, 변환 횟수

class Solution {
    private class State(val word: String, val step: Int)

    private fun convertible(original: String, comparison: String): Boolean {
        var count = 0
        for (i in original.indices) {
            if (original[i] != comparison[i]) count++
        }
        return count == 1
    }

    fun solution(begin: String, target: String, words: Array<String>): Int {
        val q = ArrayDeque<State>()
        val state = State(begin, 0)
        q.addLast(state)

        val isVisited = BooleanArray(words.size)

        while (q.isNotEmpty()) {
            val state = q.removeFirst()
            if (state.word == target) return state.step

            // 자식 하나로부터 여러 개의 자식 생성
            for (i in words.indices) {
                val word = words[i]
                val isConvertible = convertible(state.word, word)
                if (!isConvertible) continue

                if (isVisited[i]) continue
                isVisited[i] = true

                val newState = State(word, state.step + 1)
                q.addLast(newState)
            }
        }

        return 0
    }
}