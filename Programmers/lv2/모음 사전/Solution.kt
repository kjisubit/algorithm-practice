// [모음 사전]

// 재귀로 모든 경우의 수 만들어 사전 만들기
// a, aa, aaa, aaaa, aaaaa, aaaai, aaaae, aaaao, aaaau
// 해당 단어가 몇 번째 인덱스인지 확인하여 리턴

// 점화식 dfs
// (word) = word + (word + 'a') + (word + 'e') + (word + 'i') + (word + 'o') + (word + 'u')

// 종료 조건
// 단어 길이가 5

class Solution {
    private val chars = arrayOf('A', 'E', 'I', 'O', 'U')

    fun solution(word: String): Int {
        val dict = mutableListOf<String>()
        makeWord("", dict)
        return dict.indexOf(word) + 1
    }

    private fun makeWord(word: String, dict: MutableList<String>) {
        if (word.isNotEmpty()) dict.add(word)

        if (word.length == 5) return

        for (c in chars) {
            makeWord(word + c, dict)
        }
    }
}