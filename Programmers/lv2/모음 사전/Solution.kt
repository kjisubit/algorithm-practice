// [모음 사전]

// 딕셔너리 생성
// 입력값의 인덱스 위치 리턴

// 점화식
// (word) = (word) + (word + A) + (word + E) + (word + I) ...
// 구조상 (word)의 사전 저장이 필수

// 종료 조건
// word 길이 == 5

class Solution {
    private val chars = arrayOf('A', 'E', 'I', 'O', 'U')

    private fun generate(word: String, dict: MutableList<String>) {
        if (word.length > 5) return

        if (word.isNotEmpty()) dict.add(word)

        for (char in chars) {
            generate(word + char, dict)
        }
    }

    fun solution(word: String): Int {
        val dict = mutableListOf<String>()
        generate("", dict)

        return dict.indexOf(word) + 1
    }
}