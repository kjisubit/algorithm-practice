// [모음 사전]

// 생성 가능한 모든 단어 모음 만들기
// - 상태: (s) = s로 시작하는 모든 단어
// - 점화: (s) = (s + a) + (s + e) ... (s + u)
// - 종료: s.length == 5

// 입력값에 대응되는 인덱스 찾기

class Solution {
    private val chars = arrayOf('A', 'E', 'I', 'O', 'U')

    fun solution(word: String): Int {
        val dictionary = mutableListOf<String>()
        genDictionary("", dictionary)

        return dictionary.indexOf(word) + 1
    }

    private fun genDictionary(s: String, dictionary: MutableList<String>) {
        if (s.length > 5) return

        if(s.isNotEmpty()) dictionary.add(s)

        for (c in chars) {
            genDictionary(s + c, dictionary)
        }
    }
}