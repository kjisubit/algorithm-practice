// "AEIOU"의 char 어레이 선언
// 재귀를 통해 "알파벳으로 생성 가능한 길이 5 이하의 모든 단어" 만들기
// 단어가 어떤 규칙으로 생성되는지 파악 -> 제일 중요한 파트이니 아래 괄호를 다시 채워보도록
// (상태, 종료, 점화식은 어떻게 만들면 좋을까?)
// 1. 상태: (chars): chars 로 시작하는 단어
// 2. 종료: 단어 길이가 5
// 3. 점화식 설계
// -> (chars) = (chars + a) + (chars + e) + (chars + i) + (chars + o) + (chars + u)

class Solution {
    private val chars = charArrayOf('A', 'E', 'I', 'O', 'U')
    fun solution(word: String): Int {
        val words = mutableListOf<String>()
        generate("", words)
        return words.indexOf(word)
    }

    private fun generate(word: String, words: MutableList<String>) {
        words.add(word)

        if (word.length == 5) return

        for (c in chars) {
            generate(word + c, words)
        }
    }
}