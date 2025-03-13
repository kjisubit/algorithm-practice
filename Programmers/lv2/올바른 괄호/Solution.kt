class Solution {
    fun solution(s: String): Boolean {
        var counter = 0

        for (c in s) {
            when (c) {
                '(' -> {
                    counter++
                }

                ')' -> {
                    if (counter-- == 0) return false
                }
            }
        }
        return counter == 0
    }
}