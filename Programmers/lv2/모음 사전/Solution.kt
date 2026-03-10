// [모음 사전]

// 프로세스
// -- 생성 가능한 모든 단어로 구성된 사전 만들기
// -- 입력 단어가 위치한 인덱스 탐색

// 메서드 -> makeDictionary
// -- 재귀 필요
// -- 상태: word로 시작하는 단어 (word)
// -- 점화식: (word) = word + (word + A) + (word + E) + ...  + (word + U)
// -- 종료: word의 길이가 5인 경우

class Solution {
    private val chars = arrayOf('A', 'E', 'I', 'O', 'U')

    private fun makeDictionary(word: String, dictionary: MutableList<String>) {
        if (word.length > 5) return

        if (word.isNotEmpty()) dictionary.add(word)

        for (char in chars) {
            val newWord = word + char
            makeDictionary(newWord, dictionary)
        }
    }

    fun solution(word: String): Int {
        val dictionary = mutableListOf<String>()
        makeDictionary("", dictionary)

        val index = dictionary.indexOf(word)
        return index + 1
    }
}