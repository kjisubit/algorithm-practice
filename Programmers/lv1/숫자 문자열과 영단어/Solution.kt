class Solution {
    private val words = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun solution(s: String): Int {
        var string = s
        words.forEachIndexed { index, numberString ->
            string = string.replace(numberString, index.toString())
        }
        return string.toInt()
    }
}