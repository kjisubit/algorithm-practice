// [숫자 문자열과 영단어]

class Solution {
    private val array = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun solution(s: String): Int {
        var answer = s
        array.forEachIndexed { i, num ->
            answer = answer.replace(num, i.toString())
        }
        return answer.toInt()
    }
}