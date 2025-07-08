// [숫자 문자열과 영단어]

class Solution {
    val array = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun solution(s: String): Int {
        var string = s
        array.forEachIndexed { i, value ->
            string = string.replace(value, i.toString())
        }
        return string.toInt()
    }
}